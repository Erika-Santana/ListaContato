package br.edu.ifsp.dmo1.listacontatos.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.edu.ifsp.dmo1.listacontatos.R
import br.edu.ifsp.dmo1.listacontatos.databinding.ContactItemLayoutBinding
import br.edu.ifsp.dmo1.listacontatos.model.Contact

class ListContactAdapter(context: Context, data: List<Contact>) :
    ArrayAdapter<Contact>(context, R.layout.contact_item_layout, data) {
    override fun getView(position: Int, convertView: View?, parent:
    ViewGroup): View {

        val binding: ContactItemLayoutBinding
        if (convertView == null) {
            binding = ContactItemLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ContactItemLayoutBinding
        }
        val currentContact = getItem(position)
        binding.textviewItemName.text = currentContact?.name
        binding.textviewItemPhonenumber.text = currentContact?.phone
        return binding.root
    }
}