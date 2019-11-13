package bhanu.basis.model

import com.google.gson.annotations.SerializedName

/**
 * Created by: Bhanu Prakash
 * Created on:20:42, 11,November,2019
 */
data class Data(
    val id:String,
    val text:String
)
data class DataList(val data:ArrayList<Data>)