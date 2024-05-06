package xyz.droidev.youtubeclone.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
@Composable
fun SearchTextField(
    onSearch : (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember {
        mutableStateOf("")
    }
    var hasFocus by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
            onSearch(query)
        },
        placeholder = { Text("Search") },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
                focusManager.clearFocus()
            }
        ),
        trailingIcon = {
            if(hasFocus || query.isNotEmpty()){
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Clear",
                    modifier = Modifier.clickable {
                        query = ""
                        focusManager.clearFocus()
                        onSearch(query)
                    }
                )
            }
        },
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier
            .onFocusEvent { hasFocus = it.isFocused }
            .then(modifier)
    )
}