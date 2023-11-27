package com.bumblebeeai.spire.job_details.presentation.manager

import androidx.lifecycle.viewModelScope
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.azmiradi.android_base.presentation.manager.BaseViewModel
import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.Resource
import com.bumblebeeai.spire.BuildConfig
import com.bumblebeeai.spire.job_details.domain.models.LocationDirection
import com.bumblebeeai.spire.job_details.domain.models.UpdateJobStatusRequest
import com.bumblebeeai.spire.job_details.domain.usecases.UpdateJobStatusUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class JobDetailsViewModel @Inject constructor(
    private val updateJobStatusUseCase: UpdateJobStatusUseCase,
) : BaseViewModel<JobDetailsState, JobDetailsEvent>() {
    override fun createInitialState(): JobDetailsState {
        return JobDetailsState()
    }

    override fun onEvent(event: JobDetailsEvent) {
        super.onEvent(event)
        when (event) {
            is JobDetailsEvent.UpdateJobDetails -> {
                updateJobStatus(event.updateJobStatusRequest)
            }

            is JobDetailsEvent.GetJobLocationDirections -> getDirections(
                event.origin,
                event.destination
            )
        }
    }

    private fun updateJobStatus(updateJobStatusRequest: UpdateJobStatusRequest) {
        updateJobStatusUseCase.invoke(
            updateJobStatusRequest
        ).onEach {
            when (it) {
                is Resource.Failure -> {
                    emit(JobDetailsState(error = it.exception))
                }

                Resource.Loading -> {
                    emit(JobDetailsState(loading = true))
                }

                is Resource.Success -> {
                    emit(JobDetailsState(updateJobStatus = it.model))
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getDirections(origin: LatLng, destination: LatLng) {
        GoogleDirection.withServerKey(BuildConfig.GOOGLE_API_KEY)
            .from(origin)
            .to(destination)
            .execute(object : DirectionCallback {
                override fun onDirectionSuccess(direction: Direction?) {
                    if (direction != null) {
                        if (direction.isOK) {
                            val route: Route? = direction.routeList?.getOrNull(0)
                            val data = LocationDirection(
                                route?.legList?.getOrNull(0)?.directionPoint,
                                route?.legList?.getOrNull(0)?.duration?.text,
                                route?.legList?.getOrNull(0)?.distance?.text,
                            )
                            emit(JobDetailsState(locationDirection = data))
                        } else {
                            emit(JobDetailsState(error = BaseException.Unknown(direction.errorMessage)))
                        }
                    } else {
                        emit(JobDetailsState(error = BaseException.Unknown("UnKnown Error")))
                    }
                }

                override fun onDirectionFailure(throwable: Throwable) {
                    emit(JobDetailsState(error = BaseException.Unknown(throwable.message)))
                }
            })
    }


}