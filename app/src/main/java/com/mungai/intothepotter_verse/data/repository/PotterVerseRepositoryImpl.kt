package com.mungai.intothepotter_verse.data.repository

import com.mungai.intothepotter_verse.common.Resource
import com.mungai.intothepotter_verse.data.local.PotterVerseDatabase
import com.mungai.intothepotter_verse.data.remote.ApiService
import com.mungai.intothepotter_verse.domain.model.Spell
import com.mungai.intothepotter_verse.domain.repository.PotterVerseRepository
import com.mungai.potterpedia.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PotterVerseRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    database: PotterVerseDatabase
) : PotterVerseRepository {

    // DAO for accessing the database
    private val dao = database.dao

    /**
     * Retrieves a list of spells either from the local database or the remote API.
     * Emits a loading state before making the request, and then either a success state with the
     * retrieved data or an error state if there was an exception.
     * If there are no spells in the local database, fetches them from the remote API and saves them
     * to the local database.
     *
     * @return A flow of Resource<List<Spell>> that emits a loading state, followed by either a success
     * state with the retrieved data or an error state if there was an exception.
     */
    override fun getSpells(): Flow<Resource<List<Spell>>> {
        return flow {
            // Emit a loading state
            emit(Resource.Loading())

            // Get all spells from the local database
            val localData = dao.getAllSpells()

            // If there are no spells in the local database, fetch them from the remote API
            if (localData.isEmpty()) {
                try {
                    // Fetch spells from the API and map them to entities
                    val response = apiService.getAllSpells().map { it.toSpellEntity() }
                    dao.insertSpells(spells = response)
                    emit(Resource.Success(data = response.map { it.toSpell() }))
                } catch (e: HttpException) {
                    // Emit an error state if there was an HTTP exception
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                } catch (e: IOException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                }
            } else {
                // Emit the spells from the local database as success state
                emit(Resource.Success(data = localData.map { it.toSpell() }))
            }
        }.catch { e ->
            // Emit an error state if there was an exception
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
     * This function returns a Flow of a Resource of a list of Characters who are part of the main cast.
     * It emits a loading state, tries to get the data from the local database, and emits it as a success state.
     * If there is no data in the local database, it tries to fetch the data from the API and stores it in the database.
     * Finally, it emits the data as a success state. If there's an error, it emits an error state.
     */
    override fun getMainCast(): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading())
            val localData = dao.getMainCast()
            if (localData.isEmpty()) {
                try {
                    val response = apiService.getAllCharacters().map { it.toCharacterEntity() }
                    dao.insertCharacters(characters = response)
                    val data = dao.getMainCast()
                    emit(Resource.Success(data = data.map { it.toCharacter() }))
                } catch (e: HttpException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                } catch (e: IOException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                }
            } else {
                emit(Resource.Success(data = localData.map { it.toCharacter() }))
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
     * This function returns a Flow of a Resource of a list of staff Characters.
     * It emits a loading state and tries to get the data from the local database.
     * If the local data is empty, it fetches the data from the API, inserts it into the
     * local database, and returns the staff data as a success state.
     * If there's an error, it emits an error state.
     */
    override fun getStaff(): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading())
            val localData = dao.getStaff()
            if (localData.isEmpty()) {
                try {
                    val response = apiService.getAllCharacters().map { it.toCharacterEntity() }
                    dao.insertCharacters(characters = response)
                    val data = dao.getStaff()
                    emit(Resource.Success(data = data.map { it.toCharacter() }))
                } catch (e: HttpException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                } catch (e: IOException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                }
            } else {
                emit(Resource.Success(data = localData.map { it.toCharacter() }))
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
     * This function returns a Flow of a Resource of a list of all student Characters.
     * It first emits a loading state and tries to get the data from the local database.
     * If the local data is empty, it fetches the data from the API and inserts it into the database.
     * Finally, it emits the data as a success state. If there's an error, it emits an error state.
     */
    override fun getStudents(): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading())
            val localData = dao.getStudents()
            if (localData.isEmpty()) {
                try {
                    val response = apiService.getAllCharacters().map { it.toCharacterEntity() }
                    dao.insertCharacters(characters = response)
                    val data = dao.getStudents()
                    emit(Resource.Success(data = data.map { it.toCharacter() }))
                } catch (e: HttpException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                } catch (e: IOException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                }
            } else {
                emit(Resource.Success(data = localData.map { it.toCharacter() }))
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
     * Retrieves a list of all the characters from the API or local database and returns them as a flow of Resource objects.
     * If the characters are not found in the local database, the function retrieves them from the API and saves them to the database before returning them.
     * If an error occurs during the retrieval process, the function returns a Resource object with an error message.
     * @return A flow of Resource objects containing a list of Character objects.
     */
    override fun getAllCharacters(): Flow<Resource<List<Character>>> {
        return flow<Resource<List<Character>>> {
            emit(Resource.Loading())

            try {
                val data = dao.getAllCharacters().map { it.toCharacter() }
                emit(Resource.Success(data = data))

            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage))
                e.printStackTrace()
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
     *This function returns a Flow of a Resource of a single Character
     *by their ID. It emits a loading state, tries to get the data
     *from the local database and emits it as a success state.
     *If there's an error, it emits an error state.
     */
    override fun getCharacterById(characterId: String): Flow<Resource<Character>> {
        return flow<Resource<Character>> {
            emit(Resource.Loading())
            try {
                val data = dao.getCharacterById(characterId = characterId).toCharacter()
                emit(Resource.Success(data = data))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage))
                e.printStackTrace()
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
     * This function returns a Flow of a Resource of a list of Characters
     * from a given Hogwarts house. It emits a loading state, tries to get
     * the data from the local database and emits it as a success state.
     * If there's an error, it emits an error state.
     * @param house: the Hogwarts house of the requested characters
     * @return a Flow of Resource<List<Character>> representing the result of the request
     */
    override fun getCharactersByHouse(house: String): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = dao.getCharactersByHouse(house = house).map { it.toCharacter() }
                emit(Resource.Success(data = data))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage))
                e.printStackTrace()
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
     * This function returns a Flow of a Resource of a list of Characters
     * whose name matches the given name. It emits a loading state, tries to get the data
     * from the local database and emits it as a success state.
     * If there's an error, it emits an error state.
     */
    override fun getCharactersByName(name: String): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = dao.getCharactersByName(name = name).map { it.toCharacter() }
                emit(Resource.Success(data = data))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage))
                e.printStackTrace()
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
     * This function returns a Flow of a Resource of a list of Characters
     * that matches the specified house and name. It emits a loading state,
     * tries to get the data from the local database, and emits it as a success state.
     * If there's an error, it emits an error state.
     * @param house the house name to filter characters by
     * @param name the character name to search for
     */
    override fun getCharactersByNameAndHouse(
        house: String,
        name: String
    ): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = dao.getCharactersByNameAndHouse(house = house, name = name)
                    .map { it.toCharacter() }
                emit(Resource.Success(data = data))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage))
                e.printStackTrace()
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }

    /**
    This function returns a Flow of a Resource of a single Spell
    by its ID. It emits a loading state, tries to get the data
    from the local database and emits it as a success state.
    If there's an error, it emits an error state.
     */
    override fun getSpellById(id: String): Flow<Resource<Spell>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = dao.getSpellById(id = id).toSpell()
                emit(Resource.Success(data = data))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage))
                e.printStackTrace()
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }
}