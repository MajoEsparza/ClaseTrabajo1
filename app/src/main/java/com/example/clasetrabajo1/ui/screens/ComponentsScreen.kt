package com.example.clasetrabajo1.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.clasetrabajo1.R
import com.example.clasetrabajo1.data.model.PostCardModel
import com.example.clasetrabajo1.data.model.menumodel
import com.example.clasetrabajo1.ui.components.PostCardCompactComponent
import com.example.clasetrabajo1.ui.components.PostCardComponent
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ComponentsScreen(navController: NavHostController) {


    val menuOptions = arrayOf(
        menumodel(1, "Buttons", "buttons", Icons.Filled.AddCircle),
        menumodel(2, "Floating Buttons", "fbuttons", Icons.Filled.AccountCircle),
        menumodel(3, "Progress", "prog", Icons.Filled.Add),
        menumodel(4, "Chips", "chips", Icons.Filled.AccountBox),
        menumodel(5, "Sliders", "slide", Icons.Filled.PlayArrow),
        menumodel(6, "Switches", "switch", Icons.Filled.Check),
        menumodel(7, "Badges", "badges", Icons.Filled.ShoppingCart),
        menumodel(8, "Snack Bars", "snack", Icons.Filled.Lock),
        menumodel(9, "Alert Dialogs", "alert", Icons.Filled.Warning),
        menumodel(10, "Top app bar", "bar", Icons.Filled.Search),
        menumodel(11, "Input Fields", "fields", Icons.Filled.Email),
        menumodel(12, "Date Pickers", "pickers", Icons.Filled.DateRange),
        menumodel(13, "Pull to Refresh", "refresh", Icons.Filled.Refresh),
        menumodel(14, "Bottom Sheet", "sheets", Icons.Filled.KeyboardArrowUp),
        menumodel(15, "Segmented Buttons", "segmented", Icons.Filled.List)

    )

    var option by rememberSaveable { mutableStateOf("buttons") }
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                LazyColumn {
                    items(menuOptions) { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = "") },
                            label = { Text(item.title) },
                            selected = false,
                            onClick = {
                                option = item.option
                                scope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        when (option) {
            "buttons" -> {
                Buttons()
            }

            "fbuttons" -> {
                FloatingButtons()
            }

            "prog" -> {
                Progress()
            }

            "chips" -> {
                Chips()
            }

            "slide" -> {
                Sliders()
            }

            "switch" -> {
                Switches()
            }

            "badges" -> {
                Badges()
            }

            "snack" -> {
                SnackBars()
            }

            "alert" -> {
                AlertDialogs()
            }

            "bar" -> {
                Bars()
            }

            "fields" -> {
                InputFields()
            }

            "pickers" -> {
                DatePicker()
            }

            "refresh" -> {
                PullToRefresh()
            }

            "sheets" -> {
                BottomSheet()
            }

            "segmented" -> {
                SegmentedButtons()
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun Buttons() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //Button styles
        Button(onClick = {}) {
            Text("Filled")
        }

        FilledTonalButton(onClick = {}) {
            Text("Tonal")
        }

        OutlinedButton(onClick = {}) {
            Text("Outline")
        }

        ElevatedButton(onClick = {}) {
            Text("Elevated")
        }

        TextButton(onClick = {}) {
            Text("Text")
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun FloatingButtons() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        SmallFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        LargeFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        //Extended floating action button can display a text
        ExtendedFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
            Text("Button")
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun Progress() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
        )
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun Chips() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AssistChip(
            onClick = {},
            label = { Text("AssistChip") },
            leadingIcon = {
                Icon(
                    Icons.Filled.AccountBox,
                    contentDescription = "Assist Chip",
                    modifier = Modifier
                        .size(AssistChipDefaults.IconSize)
                )
            }
        )
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            //selected defines if the button is pressed or not
            selected = selected,
            //This will switch the current state
            // of FilterChip to the opposite
            onClick = { selected = !selected },
            label = { Text("Filter Chip") },
            leadingIcon = if (selected) {
                {
                    Icon(
                        Icons.Filled.AccountBox,
                        contentDescription = "Assist Chip",
                        modifier = Modifier
                            .size(AssistChipDefaults.IconSize)
                    )
                }
            } else {
                null
            }
        )
        InputChipExample("Dismiss", {})

    }
}

@Composable
fun InputChipExample(
    text: String,
    onDismiss: () -> Unit
) {
    var enabled by remember { mutableStateOf(true) }
    if (!enabled) return
    InputChip(
        label = { Text(text) },
        selected = enabled,
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "User Icon",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Cross Icon",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )
}

//@Preview
@Composable
fun Sliders() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        var SliderPosition by remember { mutableStateOf(50f) }
        Slider(
            value = SliderPosition,
            //updates the slider state according to the users interaction with it
            onValueChange = { SliderPosition = it },
            steps = 10,
            valueRange = 0f..100f,
        )
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            text = SliderPosition.toString()
        )
    }
}

//@Preview
@Composable
fun Switches() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        var checked by remember { mutableStateOf(true) }
        Switch(
            checked = checked,
            onCheckedChange = { checked = it },
        )
        var checked2 by remember { mutableStateOf(true) }
        Switch(
            checked = checked2,
            onCheckedChange = { checked2 = it },
            //adding an icon that will show only when the switch is active
            thumbContent = if (checked2) {
                {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Check Switch",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            } else {
                null
            }
        )
        var checked3 by remember { mutableStateOf(true) }
        Checkbox(
            checked = checked3,
            onCheckedChange = { checked3 = it }
        )
    }
}

//@Preview
@Composable
fun Badges() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        var itemCount by remember { mutableStateOf(0) }
        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                    {
                        Text(itemCount.toString())
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Shopping Cart icon"
            )
        }
        Button(
            onClick = { itemCount++ }
        ) {
            Text("Add item")
        }
    }
}

//@Preview
@Composable
fun SnackBars() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {

        val snackState = remember { SnackbarHostState() }
        val snackScope = rememberCoroutineScope()

        SnackbarHost(hostState = snackState)

        fun launchSnackBar() {
            snackScope.launch { snackState.showSnackbar("The message was sent") }
        }
        Button(::launchSnackBar) {
            Text("Send Message")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AlertDialogs() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        var showAlertDialog by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }

        //without "== true" statement, the conditional is checking for the var to be true
        if (showAlertDialog) {
            AlertDialog(
                icon = { Icon(Icons.Filled.Info, contentDescription = "Info Icon") },
                title = { Text("Confirm Deletion") },
                text = { Text("Do you really want to delete this file?") },
                //to close the dialog when clicking any part of the screen that is not the Alert Dialog
                onDismissRequest = {},
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Confirmed"
                            showAlertDialog = false
                        }
                    ) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Canceled"
                            showAlertDialog = false
                        }
                    ) {
                        Text("No")
                    }
                }
            )
        }
        Button(onClick = { showAlertDialog = true }) {
            Text("Delete File")
        }
        Text(selectedOption)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bars() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LargeTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary
            ),
            title = { Text("Screen Title") },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search button")
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings button"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {


            Adaptive()
        }

        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.Create, contentDescription = "")
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "")
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun Adaptive() {
    var windowsSize = currentWindowAdaptiveInfo().windowSizeClass
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    // Compact width < 600 dp Phone Portrait
    //Medium width >= 600 dp < 840 dp Tablet Portrait
    //Expanded width >= 840 dp Tablet Landscape

    //Compact height < 480 dp Phone Landscape
    //Medium height > 480 dp < 900 dp Tablet Landscape Phone Portrait
    //Expanded height >= 900 dp Tablet Portrait

    val arrayPosts = arrayOf(
        PostCardModel(1, "Title 1", "Text 1", R.drawable.totoro),
        PostCardModel(2, "Title 2", "Text 2", R.drawable.totoro),
        PostCardModel(3, "Title 3", "Text 3", R.drawable.totoro),
        PostCardModel(3, "Title 3", "Text 3", R.drawable.totoro),
        PostCardModel(4, "Title 1", "Text 1", R.drawable.totoro),
        PostCardModel(5, "Title 2", "Text 2", R.drawable.totoro),
        PostCardModel(6, "Title 3", "Text 3", R.drawable.totoro),
        PostCardModel(7, "Title 1", "Text 1", R.drawable.totoro),
        PostCardModel(8, "Title 2", "Text 2", R.drawable.totoro),
        PostCardModel(9, "Title 3", "Text 3", R.drawable.totoro)
    )

    if (width == WindowWidthSizeClass.COMPACT) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(arrayPosts) { item ->
                PostCardComponent(
                    item.id,
                    item.title,
                    item.text,
                    item.image
                )
            }
        }
    } else if (height == WindowHeightSizeClass.COMPACT) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(arrayPosts) { item ->
                PostCardCompactComponent(
                    item.id,
                    item.title,
                    item.text,
                    item.image
                )
            }
        }
    }
}

@Composable
fun InputFields() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var name by remember { mutableStateOf("") }
        var password by remember { mutableStateOf(" ") }
        var email by remember { mutableStateOf(" ") }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre ") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico ") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña ") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                println("Nombre: $name")
                println("Email: $email")
                println("Contraseña: $password")

                // Limpiar los campos después de enviar
                name = ""
                email = ""
                password = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Enviar")
        }
    }
}

@Composable
fun DatePicker() {
    var selectedDate by remember { mutableStateOf("Selecciona una fecha") }
    val context = LocalContext.current

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text("Fecha") },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        selectedDate = "$dayOfMonth/${month + 1}/$year"
                    },
                    2000, 3, 17
                )
                datePickerDialog.show()
            }
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun PullToRefresh() {
    var itemsList by remember {
        mutableStateOf(
            listOf(
                "Hola Mundo 1",
                "Hola Mundo 2",
                "Hola Mundo 3"
            )
        )
    }
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                delay(2000) // Simula una carga
                itemsList = listOf(
                    "Elemento Actualizado 1",
                    "Elemento Actualizado 2",
                    "Elemento Actualizado 3"
                )
                isRefreshing = false
            }
        }
    ) {
        LazyColumn {
            items(itemsList) { item ->
                Text(text = item, modifier = Modifier.padding(16.dp))
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet() {
    val sheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<String?>(null) }

    val itemsList = listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showSheet = true }) {
                Text("Mostrar Opciones")
            }
            selectedItem?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Seleccionaste: $it",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState
            ) {
                Text(
                    text = "Selecciona una opción",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn {
                    items(itemsList) { item ->
                        Text(
                            text = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedItem = item
                                    scope.launch { sheetState.hide() }
                                        .invokeOnCompletion {
                                            showSheet = false
                                        }
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SegmentedButtons() {
    var selectedOptions by remember { mutableStateOf(setOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Seleccione una o más opciones:")

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val options = listOf("Opción 1", "Opción 2", "Opción 3")

            options.forEach { option ->
                ElevatedButton(
                    onClick = {
                        selectedOptions = if (option in selectedOptions) {
                            selectedOptions - option // Si ya está seleccionada, la quitamos
                        } else {
                            selectedOptions + option // Si no está seleccionada, la agregamos
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (option in selectedOptions)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        option,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Seleccionaste: ${selectedOptions.joinToString(", ")}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
