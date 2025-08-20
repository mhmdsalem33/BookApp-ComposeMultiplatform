package org.salem.bookapp.book.presentation.book_list.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import bookapp_composemultiplatform.composeapp.generated.resources.Res
import bookapp_composemultiplatform.composeapp.generated.resources.close_hint
import bookapp_composemultiplatform.composeapp.generated.resources.search_hint
import org.jetbrains.compose.resources.stringResource
import org.salem.bookapp.core.Presentation.DarkBlue
import org.salem.bookapp.core.Presentation.DesertWhite
import org.salem.bookapp.core.Presentation.SandYellow

@Composable
fun BookSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {

    CompositionLocalProvider(
        LocalTextSelectionColors provides  TextSelectionColors(
            handleColor = SandYellow,
            backgroundColor = SandYellow
        )
    ){
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            shape = RoundedCornerShape(100),
            placeholder = {
                Text(text = stringResource(Res.string.search_hint))
            },
            maxLines = 1,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = DarkBlue,
                focusedBorderColor = SandYellow,
                unfocusedBorderColor = SandYellow.copy(alpha = 0.5f),
                disabledBorderColor = Color.Gray.copy(alpha = 0.5f)

            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(0.66f)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next

            ),
            keyboardActions = KeyboardActions {
                onImeSearch()
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = searchQuery.isNotEmpty(),
                ) {
                    IconButton(
                        onClick = {
                            onSearchQueryChange("")
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(Res.string.close_hint),
                            tint = MaterialTheme.colorScheme.onSurface.copy(0.66f)
                        )
                    }
                }
            },

            modifier =  modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(100),
                    color = DesertWhite
                ).minimumInteractiveComponentSize(),
        )
    }

}
