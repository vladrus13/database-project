package ru.vladrus13.tornado.model

import com.google.gson.annotations.SerializedName
import ru.vladrus13.model.bean.Relations
import kotlin.reflect.KClass

class Container {

    @SerializedName("steps")
    var steps: Map<KClass<*>, Relations> = hashMapOf()

    @SerializedName("start")
    var relations: Relations = Relations(mutableSetOf(), null)
}