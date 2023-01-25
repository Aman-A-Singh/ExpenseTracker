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
import androidx.lifecycle.asLiveData
import com.cornerstoneondemand.expensetracker.database.Expense
import com.cornerstoneondemand.expensetracker.databinding.ActivityEditExpenseBinding
import com.cornerstoneondemand.expensetracker.utilities.*
import com.cornerstoneondemand.expensetracker.viewmodel.EditExpenseViewModel
import com.cornerstoneondemand.expensetracker.viewmodel.ThisMonthViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditExpenseActivity : AppCompatActivity() {
    private lateinit var category: Category
    private lateinit var binding: ActivityEditExpenseBinding
    lateinit var viewModel:EditExpenseViewModel
    var expenseDate = Date()
    private var expenseId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        expenseId = intent.extras!!.getInt(EXPENSE_ID,0)
        viewModel = ViewModelProvider(this).get(EditExpenseViewModel::class.java)
        initUI(expenseId)
    }

    private fun initUI(expenseId : Int) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val payment_modes = resources.getStringArray(R.array.payment_mode)

        val expense = viewModel.getExpense(expenseId).asLiveData()
        expense.observe(this) { it ->
            binding.editTvAmount.setText(it.amount.toString())
            category = it.category_id
            binding.category.text = getCategoryName(it.category_id.value)
            binding.editTextWriteNote.setText(it.note.toString())
            expenseDate = it.date
            binding.textViewDate.text = dateFormat.format(it.date)
            binding.spinnerPaymentMode.setSelection(payment_modes.indexOf(it.payment_mode))
        }


        val arrayAdapter = ArrayAdapter(this, R.layout.custom_dropdown_payment_mode, payment_modes)
        binding.spinnerPaymentMode.adapter = arrayAdapter

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

        viewModel.selectedDate.observe(this, { date ->
            binding.textViewDate.setText(dateFormat.format(date))
            expenseDate = date
        })


        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Edit Transaction"

        binding.category.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivityForResult(intent, CATEGORY_REQUEST)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.edit_expense_menu, menu)
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
            val expense = Expense(expenseId, amount, category, note, date, mode)
            viewModel.update(expense)
            finish()
        }
    }
}