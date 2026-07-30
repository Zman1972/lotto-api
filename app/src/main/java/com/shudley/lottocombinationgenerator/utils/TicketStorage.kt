package com.shudley.lottocombinationgenerator.utils

import android.content.Context
import android.content.SharedPreferences

object TicketStorage {

    private const val PREF_NAME = "lotto_storage"
    private const val KEY_TICKETS = "saved_tickets"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun save(ticket: String) {
        val tickets = getAll().toMutableSet()
        tickets.add(ticket)

        prefs.edit()
            .putStringSet(KEY_TICKETS, tickets)
            .apply()
    }

    fun getAll(): List<String> {
        return prefs.getStringSet(KEY_TICKETS, emptySet())
            ?.toList()
            ?.sorted()
            ?: emptyList()
    }

    fun clear() {
        prefs.edit()
            .remove(KEY_TICKETS)
            .apply()
    }

    fun delete(ticket: String) {
        val tickets = getAll().toMutableSet()
        tickets.remove(ticket)

        prefs.edit()
            .putStringSet(KEY_TICKETS, tickets)
            .apply()
    }
}