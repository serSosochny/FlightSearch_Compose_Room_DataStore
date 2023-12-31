package com.example.flightsearch_compose_room_datastore.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightsearch_compose_room_datastore.model.Airport
import com.example.flightsearch_compose_room_datastore.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightSearchDao {

    @Query("SELECT * FROM airport WHERE iata_code LIKE '%' || :searchField || '%' OR name LIKE '%' || :searchField || '%' ORDER BY passengers DESC")
    fun getFlightsForSearchFieldFlow(searchField: String): Flow<List<Airport>> //List<Airport>

    @Query("SELECT * FROM airport WHERE iata_code != :completeSearchField ORDER BY passengers DESC")
    fun getAllFlightsFromChosenAirportFlow(completeSearchField: String): Flow<List<Airport>>

    /**
     * Взять все любимы направления полетов
     */
    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getFavoriteFlightsFlow(): Flow<List<Favorite>>

    /**
     * Удаление записей из Таблицы Favorite
     */
//    @Delete(entity = Favorite::class)
//    suspend fun deleteFromFavorites(item:Favorite)
    @Query("DELETE FROM favorite WHERE departure_code=:departureCode AND destination_code=:destinationCode")
    suspend fun deleteFromFavorites(departureCode: String, destinationCode: String)
    /**
     *Сохранить в таблицу Favorites
     */
//    @Insert(entity = Favorite::class,onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertInFavorite(item: Favorite)
    @Query("INSERT INTO favorite VALUES (NULL,:departureCode,:destinationCode)")
    suspend fun insertInFavorite(departureCode: String, destinationCode: String)

    /**
     * Получить имена аэропортов по коду / для Favorite
     */
//    @Query("SELECT name FROM airport WHERE iata_code = :code")
//    fun getAirportNameByCode(code: String): String
    @Query("SELECT * FROM airport WHERE iata_code = :code")
    suspend fun getAirportItemByCode(code: String): Airport?
}