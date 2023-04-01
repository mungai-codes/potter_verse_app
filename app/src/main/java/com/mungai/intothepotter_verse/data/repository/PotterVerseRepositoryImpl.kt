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
    private val database: PotterVerseDatabase
) : PotterVerseRepository {

    private val dao = database.dao

    override fun getAllCharacters(): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading())

            val localData = dao.getAllCharacters()

            if (localData.isEmpty()) {
                try {
                    val response = apiService.getAllCharacters().map { it.toCharacterEntity() }
                    dao.insertCharacters(characters = response)
                    emit(Resource.Success(data = response.map { it.toCharacter() }))
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

    override fun getSpells(): Flow<Resource<List<Spell>>> {
        return flow {
            emit(Resource.Loading())

            val localData = dao.getAllSpells()

            if (localData.isEmpty()) {
                try {
                    val response = apiService.getAllSpells().map { it.toSpellEntity() }
                    dao.insertSpells(spells = response)
                    emit(Resource.Success(data = response.map { it.toSpell() }))
                } catch (e: HttpException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                } catch (e: IOException) {
                    emit(Resource.Error(message = e.localizedMessage))
                    e.printStackTrace()
                }
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage))
            e.printStackTrace()
        }
    }
}