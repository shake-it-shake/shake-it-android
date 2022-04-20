package com.semicolon.shakeit.util

import androidx.paging.*
import com.semicolon.domain.entity.room.RoomPageEntity
import com.semicolon.domain.entity.room.RoomsEntity
import com.semicolon.domain.usecase.room.FetchRoomsUseCase
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class ClubPagingSource @Inject constructor(
    private val fetchRoomsUseCase: FetchRoomsUseCase
) : PagingSource<Int, RoomsEntity.RoomEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RoomsEntity.RoomEntity> {
        val page = params.key ?: 0
        return try {
            val rooms = fetchRoomsUseCase
                .execute(RoomPageEntity(page = page, size = params.loadSize)).rooms
            LoadResult.Page(
                data = rooms,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (rooms.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RoomsEntity.RoomEntity>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
}

fun fetchRoomsByPaging(
    pageSize: Int,
    clubPagingSource: ClubPagingSource
): Flow<PagingData<RoomsEntity.RoomEntity>> =
    Pager(
        config = PagingConfig(pageSize = pageSize),
        pagingSourceFactory = { clubPagingSource }
    ).flow