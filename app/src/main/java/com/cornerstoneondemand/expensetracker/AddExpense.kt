package com.cornerstoneondemand.expensetracker

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.cornerstoneondemand.expensetracker.database.Expense
import com.cornerstoneondemand.expensetracker.databinding.ActivityAddExpenseBinding
import com.cornerstoneondemand.expensetracker.utilities.*
import com.cornerstoneondemand.expensetracker.viewmodel.AddExpenseViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddExpense : AppCompatActivity() {
    private lateinit var viewModel: AddExpenseViewModel
    private lateinit var binding: ActivityAddExpenseBinding
    private lateinit var category: Category
    private var expenseDate = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.editTvAmount.setHint("₹ 0")
        viewModel = ViewModelProvider(this).get(AddExpenseViewModel::class.java)

        binding.textViewDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, day ->
                    viewModel.selectedDate.value = Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, day)
                    }.time
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        viewModel.selectedDate.observe(this, { date ->
            binding.textViewDate.setText(dateFormat.format(date))
            expenseDate = date
        })

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Add Transaction"

        val payment_modes = resources.getStringArray(R.array.payment_mode)
        val arrayAdapter = ArrayAdapter(this, R.layout.custom_dropdown_payment_mode, payment_modes)
        binding.spinnerPaymentMode.adapter = arrayAdapter

        binding.category.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivityForResult(intent, CATEGORY_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.add_expense_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_expense -> {
                saveExpense()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CATEGORY_REQUEST && resultCode == Activity.RESULT_OK) {
            val result: Int = data!!.getIntExtra(CATEGORY_RESULT, 0)
            binding.category.text = getCategoryName(result)
            category = Category.fromInt(result)!!
            binding.category.error=null
        }
    }

    private fun saveExpense() {

        if (binding.editTvAmount.text.isEmpty()) {
            binding.editTvAmount.error = "Enter Valid Amount"
        } else if (!::category.isInitialized) {
            binding.category.error = "Select Valid Category"
        } else if (binding.editTextWriteNote.text.isEmpty()) {
            binding.editTextWriteNote.error = "Enter Note"
        } else {
            val amount = binding.editTvAmount.text.toString().trim().toDouble()
            val note = binding.editTextWriteNote.text.toString().trim()
            val mode = binding.spinnerPaymentMode.selectedItem.toString().trim()
            val date: Date = expenseDate
            val expense = Expense(0, amount, category, note, date, mode)
            viewModel.insert(expense)
            finish()
        }

    }
}