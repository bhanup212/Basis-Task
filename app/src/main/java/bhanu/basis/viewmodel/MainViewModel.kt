package bhanu.basis.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bhanu.basis.model.DataList
import bhanu.basis.network.ApiClient
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by: Bhanu Prakash
 * Created on:20:28, 11,November,2019
 */
class MainViewModel(private val apiClient: ApiClient):ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName
    private val disposable = CompositeDisposable()
    private var gson = Gson()
    private val isLoading = MutableLiveData<Boolean>()
    private val dataList = MutableLiveData<DataList>()

    fun isLoading():LiveData<Boolean> = isLoading
    fun dataList():LiveData<DataList> = dataList
    fun getCardsList(){

        isLoading.postValue(true)
        disposable.add(
            apiClient.getCardList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<String>() {
                    override fun onSuccess(s: String) {
                        val formattedResponse = s.substring(1,s.length)
                        val data:DataList = gson.fromJson(formattedResponse, DataList::class.java)
                        dataList.postValue(data)
                        isLoading.postValue(false)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG,"onError: ${e.message}")
                        isLoading.postValue(false)
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed){
            disposable.dispose()
        }
    }
}