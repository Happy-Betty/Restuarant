package com.happy.restaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var adapter:FoodAdapter?= null
    var listOfFoods = ArrayList<Foods>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // loading foods
        listOfFoods.add(Foods("Chicken", "This chicken is seasoned with the freshest of natural spices and deep fried in pure organic sunflower oil, served with french fries", R.drawable.chicken))
        listOfFoods.add(Foods("Coffee","   Coffee preparation is the process of turning coffee beans into a beverage. While the particular steps vary with the type of coffee and with the raw materials, the process includes four basic steps; raw coffee beans must be roasted, the roasted coffee beans must then be ground, the ground coffee must then be mixed with hot water for a certain time (brewed), and finally the liquid coffee must be separated from the used grounds",R.drawable.coffee_pot))
        listOfFoods.add(Foods("Espresso","    Espresso’s authentic formula is clear and basic, its proper execution a matter of training, experience and natural talent.  A jet of hot water at 88°-93°C (190°-200°F) passes under a pressure of nine or more atmospheres through a seven-gram (.25 oz) cake-like layer of ground and tamped coffee. Done right, the result is a concentrate of not more than 30 ml (one oz) of pure sensorial pleasure" ,R.drawable.espresso))
        listOfFoods.add(Foods("French Fries","   Heat a few inches of vegetable oil to 300 degrees F in a heavy pot. In 3 or 4 batches, fry the potatoes about 4 to 5 minutes per batch, or until soft. They should not be brown at all at this point-you just want to start the cooking process. Remove each batch and drain them on new, dry paper towels",R.drawable.french_fries))
        listOfFoods.add(Foods("Honey"," While it is less likely that anyone would do this on their own if they are not a beekeeper, this might be useful for those who aspire to become one.Bees are really great and easy to keep, even in the urban environment! As Novella Carpenter calls them, bees are &quot;gateway animal for urban farmers&quot;. All you need is some space in the backyard/deck. The process of honey harvesting and extraction most likely happens on a separate days. These are the tools required:",R.drawable.honey))
        listOfFoods.add(Foods("Strawberry","   Preparation. Coarsely mash strawberries with sugar, lemon juice, and salt using a potato masher in a large bowl. Let stand, stirring and mashing occasionally, 10 minutes. Transfer half of strawberry mixture to a blender and purée with cream until smooth. Freeze mixture in ice cream maker.",R.drawable.strawberry_ice_cream))
        listOfFoods.add(Foods("Sugar cubes","Sugar cubes are extremely simple to make at home - all you need is sugar and water. In addition to standard cubes, you can add color and flavor to add fun flair to a tea party or another gathering. Learn how to make sugar cubes using two different methods: using a pan in the oven or an ice cube tray you leave out overnight.",R.drawable.sugar_cubes))
        adapter= FoodAdapter(this,listOfFoods)

        //Assign list to the gridview
        findViewById<GridView>(R.id.gvListFood). adapter = adapter
    }
    class FoodAdapter:BaseAdapter {
        var listOfFood= ArrayList<Foods>()
        var context:Context?=null
        constructor(context:Context,listOfFood:ArrayList<Foods>):super(){
            this.context=context
            this.listOfFood=listOfFood
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val food = this.listOfFood[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView= inflator.inflate(R.layout.food_ticket,null)
            foodView.findViewById<ImageView>(R.id.ivFoodImage).setImageResource(food.image!!)
            foodView.findViewById<ImageView>(R.id.ivFoodImage).setOnClickListener {

                val intent = Intent(context,foodDetails::class.java)
                intent.putExtra("name",food.name!!)
                intent.putExtra("des",food.des!!)
                intent.putExtra("image",food.image!!)
                context!!.startActivity(intent)
            }
            foodView.findViewById<TextView>(R.id.tvName).text =  food.name!!
            return  foodView

        }

        override fun getItem(p0: Int): Any {
            return listOfFood[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listOfFood.size
        }

    }
}
