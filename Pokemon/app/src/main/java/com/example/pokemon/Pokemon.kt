package com.example.pokemon

import android.location.Location

class Pokemon{
    var name: String?=null
    var des: String?=null
    var image: Int?=null
    var power: Double?=null
    var lat: Double?=null
    var log: Double?=null
    var isCatch:Boolean?=false
    var location: Location?=null
    constructor(image:Int,name:String,des: String,power: Double,lat: Double,log: Double,isCatch:Boolean){
        this.name=name
        this.des=des
        this.image=image
        this.power=power
        this.lat=lat
        this.log=log
        this.isCatch=isCatch
        this.location=Location(name)
        this.location!!.latitude=lat
        this.location!!.longitude=log
    }
}