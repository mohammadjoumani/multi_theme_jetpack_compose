package com.mmj.multitheme.select_theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mmj.multitheme.main.MainViewModel
import com.mmj.multitheme.ui.theme.AppTheme
import com.mmj.multitheme.app.AppPreferences
import com.mmj.multitheme.main.MainEvent
import com.mmj.multitheme.R

@Composable
fun SelectThemeDialog(
    mainViewModel: MainViewModel,
    setShowDialog: (Boolean) -> Unit,
    returnValue: (AppTheme) -> Unit,
) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
    ) {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                ItemSelectRadioButton(
                    title = "Blue Theme",
                    onClick = {
                        if (mainViewModel.stateApp.theme != AppTheme.Blue) {
                            AppPreferences.setTheme(AppTheme.Blue)
                            mainViewModel.onEvent(MainEvent.ThemeChange(AppTheme.Blue))
                            setShowDialog(false)
                            returnValue(AppTheme.Blue)
                        }
                    },
                    isSelect = mainViewModel.stateApp.theme == AppTheme.Blue
                )
                ItemSelectRadioButton(
                    title = "Red Theme",
                    onClick = {
                        if (mainViewModel.stateApp.theme != AppTheme.Red) {
                            AppPreferences.setTheme(AppTheme.Red)
                            mainViewModel.onEvent(MainEvent.ThemeChange(AppTheme.Red))
                            setShowDialog(false)
                            returnValue(AppTheme.Red)
                        }
                    },
                    isSelect = mainViewModel.stateApp.theme == AppTheme.Red
                )
                ItemSelectRadioButton(
                    title = "Green Theme",
                    onClick = {
                        if (mainViewModel.stateApp.theme != AppTheme.Green) {
                            AppPreferences.setTheme(AppTheme.Green)
                            mainViewModel.onEvent(MainEvent.ThemeChange(AppTheme.Green))
                            setShowDialog(false)
                            returnValue(AppTheme.Green)
                        }
                    },
                    isSelect = mainViewModel.stateApp.theme == AppTheme.Green
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun ItemSelectRadioButton(
    title: String,
    onClick: () -> Unit,
    isSelect: Boolean
) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = if (isSelect)
                    painterResource(id = R.drawable.ic_check_box) else
                    painterResource(id = R.drawable.ic_un_check_box),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(25.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1.0f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}