package com.example.maninthemirror.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    // TODO: Replace with your actual Supabase Project URL and Anon API Key from https://supabase.com/dashboard
    const val SUPABASE_URL = "https://indgvngxxkxcrkrimnnn.supabase.co"
    const val SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImluZGd2bmd4eGt4Y3Jrcmltbm5uIiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTE5MDM5NTAsImV4cCI6MjEwNzQ3OTk1MH0.IbkmQwniBD3Q7RC_nUS7Km3wAsqLTtKUtBxn02JZAFw"

    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_ANON_KEY
    ) {
        install(Postgrest)
    }
}
