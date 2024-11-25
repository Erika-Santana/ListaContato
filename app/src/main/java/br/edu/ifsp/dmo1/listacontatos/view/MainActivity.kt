package br.edu.ifsp.dmo1.listacontatos.view

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.listacontatos.R
import br.edu.ifsp.dmo1.listacontatos.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.listacontatos.databinding.NewContactDialogBinding
import br.edu.ifsp.dmo1.listacontatos.model.Contact
import br.edu.ifsp.dmo1.listacontatos.model.ContactDao

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private val TAG = "CONTACTS"
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListContactAdapter
    private var listDatasource = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.v(TAG, "Executando o onCreate()")
        configClickListener()
        configListview()
    }
    override fun onStart() {
        Log.v(TAG, "Executando o onStart()")
        super.onStart()
    }
    override fun onResume() {
        Log.v(TAG, "Executando o onResume()")
        super.onResume()
    }
    override fun onPause() {
        Log.v(TAG, "Executando o onPause()")
        super.onPause()
    }
    override fun onStop() {
        Log.v(TAG, "Executando o onStop()")
        super.onStop()
    }
    override fun onRestart() {
        Log.v(TAG, "Executando o onRestart()")
        super.onRestart()
    }
    override fun onDestroy() {


        Log.v(TAG, "Executando o onDestroy()")
        Log.v(TAG, "Lista de contatos que será perdida")
        for (contact in ContactDao.findAll()) {
            Log.v(TAG, contact.toString())
        }

        super.onDestroy()
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?,
                             position: Int, id: Long) {

        val selectContact =
            binding.listviewContacts.adapter.getItem(position) as Contact
        val uri = "tel:${selectContact.phone}"
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(uri)
        startActivity(intent)
    }
    private fun configClickListener() {
        binding.buttonNewContact.setOnClickListener {
            handleNewContactDialog()
        }
    }
    private fun configListview() {
        listDatasource.addAll(ContactDao.findAll())
        adapter = ListContactAdapter(this, listDatasource)
        binding.listviewContacts.adapter = adapter
        binding.listviewContacts.onItemClickListener = this
    }
    private fun updateListDatasoruce() {
        listDatasource.clear()
        listDatasource.addAll(ContactDao.findAll())
        listDatasource.sortBy { it.name }
        adapter.notifyDataSetChanged()


    }
    private fun handleNewContactDialog() {

        val bindingDialog = NewContactDialogBinding.inflate(layoutInflater)
        val builderDialog = AlertDialog.Builder(this)
        builderDialog.setView(bindingDialog.root)
            .setTitle(R.string.new_contact)
            .setPositiveButton(
                R.string.btn_dialog_save,
                DialogInterface.OnClickListener { dialog, which ->

                    Log.v(TAG, "Salvar contato")
                    ContactDao.insert(
                        Contact(
                            bindingDialog.edittextName.text.toString(),
                            bindingDialog.edittextPhone.text.toString()
                        )
                    )
                    updateListDatasoruce()
                    dialog.dismiss()
                })
            .setNegativeButton(
                R.string.btn_dialog_cancel,
                DialogInterface.OnClickListener { dialog, which ->

                    Log.v(TAG, "Cancelar novo contato")
                    dialog.cancel()
                })
        builderDialog.create().show()
    }
}