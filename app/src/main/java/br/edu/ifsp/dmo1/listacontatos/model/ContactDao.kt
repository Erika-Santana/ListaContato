package br.edu.ifsp.dmo1.listacontatos.model

import java.util.Collections
import java.util.LinkedList
object ContactDao {
    private val dataset = LinkedList<Contact>()
    fun insert(contact: Contact) {
        dataset.add(Contact(contact.name, contact.phone))
    }
    fun findAll(): List<Contact> {

        return ArrayList(dataset)
    }
}