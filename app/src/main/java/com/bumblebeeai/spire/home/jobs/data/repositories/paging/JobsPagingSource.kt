package com.bumblebeeai.spire.home.jobs.data.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumblebeeai.spire.common.domain.model.enums.JobType
import com.bumblebeeai.spire.home.jobs.data.datasources.local.IJobsLocalDataSource
import com.bumblebeeai.spire.home.jobs.data.datasources.remote.IJobsRemoteDataSource
import com.bumblebeeai.spire.home.jobs.data.mapper.DriverJobMapper
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.home.jobs.domain.model.FILTER_TYPE
import com.bumblebeeai.spire.home.jobs.domain.model.MERCHANT_ID
import com.bumblebeeai.spire.home.jobs.domain.model.PAGE
import retrofit2.HttpException
import java.io.IOException

internal class JobsPagingSource constructor(
    private val remoteDataSource: IJobsRemoteDataSource,
    private val localDataSource: IJobsLocalDataSource,
    private val jobType: JobType,
) : PagingSource<Int, DriverJob>() {

    private var query: Map<String, Any>? = null
    private suspend fun getQuery(): Map<String, Any> {
        if (query == null) {
            query = mapOf(
                MERCHANT_ID to localDataSource.getMerchantId(),
                FILTER_TYPE to jobType.filterType
            )
        }
        return query as Map<String, Any>
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DriverJob> {

        return try {
            val currentPage = params.key ?: 1
            val query = getQuery().toMutableMap().also {
                it[PAGE] = currentPage
            }

            val jobs = remoteDataSource.getJobs(
                query = query
            )

            val jobList = jobs.data?.data?.filterNotNull()
            LoadResult.Page(
                data = DriverJobMapper.dtoToDomain(jobList),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (jobs.data?.data?.isEmpty() == true) null else jobs.data?.currentPage?.plus(
                    1
                )
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
        catch (exception: Exception) {
             return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DriverJob>): Int? {
        return state.anchorPosition
    }

}