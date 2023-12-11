package com.tasty.recipesapp.ui.recipe

//https://img.buzzfeed.com…_MugCakesFourWays-FB.jpg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding

class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false)

        // Find the "Save" button in the layout
        val buttonSaveRecipe: Button = binding.root.findViewById(R.id.buttonSaveRecipe)

        // Set OnClickListener for the "Save" button
        buttonSaveRecipe.setOnClickListener {
            Log.d("SAVE", "SAVE")
        }

        return binding.root
    }

}
