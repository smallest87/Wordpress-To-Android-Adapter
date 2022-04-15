package com.example.cafeorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.core.view.isVisible
import com.example.cafeorderapp.databinding.ActivityCreateOrderBinding

class CreateOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateOrderBinding
    private var drink: String = ""
    private var name: String = ""
    private var password: String = ""
    private var builderToppings = StringBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drink = getString(R.string.hello_client)
        var toppings: String = "Дополнительно к $drink:"


        val intent = intent
        if(intent.hasExtra("name") && intent.hasExtra("password")){
            name = intent.getStringExtra("name").toString()
            password = intent.getStringExtra("password").toString()
        }else{
            name = getString(R.string.string_default_name)
            password = getString(R.string.string_default_password)
        }
        val hello: String = "Здравствуйте, $name! Кофе или чай?"
        binding.textViewHello.text = hello


        binding.radioGroupDrinks.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == R.id.radioButtonTea){
                drink = getString(R.string.tea)
                binding.spinnerTypeOfCoffee.isVisible
                binding.spinnerTypeOfCoffee.visibility = View.INVISIBLE
                binding.spinnerTypeOfTea.visibility = View.VISIBLE
                binding.checkboxLemon.isVisible = true
            }
            if (checkedId == R.id.radioButtonCoffee){
                drink = getString(R.string.coffee)
                binding.spinnerTypeOfTea.visibility = View.INVISIBLE
                binding.spinnerTypeOfCoffee.visibility = View.VISIBLE
                binding.checkboxLemon.isVisible = false
            }
            val toppings: String = "Дополнительно к $drink:"
            binding.textViewToppings.text = toppings
        }
        binding.imageViewButton.setOnClickListener {
            builderToppings.setLength(0)
            if (binding.checkboxMilk.isChecked){
                builderToppings.append(getString(R.string.milk) + " ")
            }
            if (binding.checkboxSugar.isChecked){
                builderToppings.append(getString(R.string.sugar) + " ")
            }
            if (binding.checkboxLemon.isChecked && (drink == getString(R.string.tea))){
                builderToppings.append(getString(R.string.lemon) + " ")
            }
            var typeOfDrink = if(drink == getString(R.string.tea)){
                binding.spinnerTypeOfTea.selectedItem.toString()
            } else{
                binding.spinnerTypeOfCoffee.selectedItem.toString()
            }
            var order: String = "Имя: $name \n Пароль: $password \n Напиток: $drink \n Вид напитка: $typeOfDrink"
            val toppings: String = if(builderToppings.isNotEmpty()){
                "Необходимые добавки: $builderToppings"
            }else{
                ""
            }
            var fullOrder = order + toppings
            var intent = Intent(this, OrderDetailActivity::class.java)
            intent.putExtra("order", fullOrder)
            startActivity(intent)
        }

    }
    private fun showView(view:View) {
        view.visibility = View.VISIBLE
    }
    private fun hideView(view:View) {
        view.visibility =  View.INVISIBLE

    }
}


