package com.example.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {
    var listOfAnimals= ArrayList<Animal>()
    var adapter: AnimalAdaptor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load animals
        listOfAnimals.add(Animal("Baboon","Baboon is a monkey breed",R.drawable.baboon,false))
        listOfAnimals.add(Animal("BullDog","Bulldog is a dog breed",R.drawable.bulldog,false))
        listOfAnimals.add(Animal("Panda","Panda is always sleepy",R.drawable.panda,true))
        listOfAnimals.add(Animal("Bird","Swallow bird is a bird breed",R.drawable.swallow_bird,true))
        listOfAnimals.add(Animal("White Tiger","Bengal white tiger is a very rare species",R.drawable.white_tiger,false))
        listOfAnimals.add(Animal("Zebra","Zebra are considered to be horse breed",R.drawable.zebra,true))
        adapter= AnimalAdaptor(this,listOfAnimals)
        tvListAnimal.adapter=adapter
    }

    fun delete(index:Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }


    class AnimalAdaptor : BaseAdapter {
        var listOfAnimals= ArrayList<Animal>()
        var context: Context?=null
        constructor(context:Context,listOfAnimals: ArrayList<Animal>): super(){
            this.listOfAnimals=listOfAnimals
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal=listOfAnimals[p0]
            if (animal.isKiller==true) {
                var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.animal_killer_ticket,null)
                myView.tvName.text=animal.name!!
                myView.tvDes.text=animal.des!!
                myView.tvImage.setImageResource(animal.image!!)
                myView.tvImage.setOnClickListener{
                    val intent= Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("desc",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }else{
                var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.animal_ticket,null)
                myView.tvName.text=animal.name!!
                myView.tvDes.text=animal.des!!
                myView.tvImage.setImageResource(animal.image!!)
                myView.tvImage.setOnClickListener{
                    val intent= Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("desc",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }
        }
        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong();
        }

        override fun getCount(): Int {
           return listOfAnimals.size
        }

    }




}