package model

import com.google.gson.annotations.SerializedName
import database.bean.Relations
import kotlin.reflect.KClass

class Container {

    @SerializedName("steps")
    var steps: Map<KClass<*>, Relations> = hashMapOf()

    @SerializedName("start")
    var relations: Relations = Relations(mutableSetOf(), null)
}