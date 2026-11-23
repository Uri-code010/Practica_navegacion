package com.example.practica
import android.provider.ContactsContract
import android.provider.Settings
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Contacts
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.practica.ui.theme.PracticaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    private var keepSplash = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen=installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplash }
        lifecycleScope.launch {
            delay(3000)
            keepSplash = false
        }
        super.onCreate(savedInstanceState)
        ShowToast("Función onCreate()")
        enableEdgeToEdge()
        setContent {
            PracticaTheme {
                ViewContainer()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        ShowToast("Función on Start()")
    }

    override fun onResume()
    {
        super.onResume()
        ShowToast("Función onResume()")
    }

    override fun onPause(){
        super.onPause()
        ShowToast("Función onPause()")
    }
    override fun onStop() {
        super.onStop()
        ShowToast("Función onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        ShowToast("Función onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        ShowToast("Función onDestroy()")
    }
    private fun ShowToast(message: String) {
        val toast = Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        )
        toast.show()
        Log.i(TAG, message)
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentView(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableStateOf(0) }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        item {
            Image(
                painter = painterResource(id = R.drawable.urbana),
                contentDescription = "Ilustración Android",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.padding(top = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable { counter++ }
                        .padding(start = 12.dp)
                )
                Text(
                    text = counter.toString(),
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        // LazyRow con las 5 imágenes desde image a image5.png
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imageList = listOf(
                    R.drawable.image,
                    R.drawable.image2,
                    R.drawable.image3,
                    R.drawable.image4,
                    R.drawable.image5
                )
                items(imageList) { imageRes ->
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Imagen de galería",
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                }
            }
        }

        item {
            Text(
                text = stringResource(R.string.materia),
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        item {
            Text(
                text = stringResource(R.string.nombre),
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        item {
            Text(
                text = stringResource(R.string.apellido),
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ViewContainerPreview() {
    PracticaTheme {
        ViewContainer()
    }
}

@Preview(name = "English Preview", showBackground = true, locale = "en")
@Composable
fun EnglishPreview() {
    PracticaTheme {
        ViewContainer()
    }
}

@Preview(name = "Spanish Preview", showBackground = true, locale = "es")
@Composable
fun SpanishPreview() {
    PracticaTheme {
        ViewContainer()
    }
}

@Preview(name = "French Preview", showBackground = true, locale = "fr")
@Composable
fun FrenchPreview() {
    PracticaTheme {
        ViewContainer()
    }
}

@Preview(name = "Greek Preview", showBackground = true, locale = "el")
@Composable
fun GreekPreview() {
    PracticaTheme {
        ViewContainer()
    }
}

@Composable
fun DropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.options_menu),
                tint = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.profile)) },
                onClick = {
                    expanded = false
                    // Acción para Perfil
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(R.string.config)) },
                onClick = {
                    expanded = false
                    // Acción para Configuración
                },
                leadingIcon = {
                    Icon(Icons.Default.Settings, contentDescription = null)
                }
            )

            HorizontalDivider()

            // ---- NUEVOS ÍTEMS ----
            DropdownMenuItem(
                text = { Text(stringResource(R.string.call)) },
                onClick = {
                    expanded = false
                    openDialer(context)
                },
                leadingIcon = {
                    Icon(Icons.Default.Call, contentDescription = null)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(R.string.bluetooth)) },
                onClick = {
                    expanded = false
                    openBluetoothSettings(context)
                },
                leadingIcon = {
                    Icon(Icons.Default.Bluetooth, contentDescription = null)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(R.string.contacts)) },
                onClick = {
                    expanded = false
                    openContacts(context)
                },
                leadingIcon = {
                    Icon(Icons.Default.Contacts, contentDescription = null)
                }
            )

            HorizontalDivider()

            DropdownMenuItem(
                text = { Text(stringResource(R.string.logout)) },
                onClick = {
                    expanded = false
                    // Acción para Cerrar sesión
                },
                leadingIcon = {
                    Icon(Icons.Default.ExitToApp, contentDescription = null)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewContainer() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val fabMessage = stringResource(R.string.home_fab_text)
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItem by remember { mutableStateOf("My files") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Header
                Text(
                    text = "Application Menu",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider()

                // Subtitle
                Text(
                    text = "Storage",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                // Items: My files, Shared with me, Recent, Trash (Active / Inactive text handled by selected state)
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Folder, contentDescription = null) },
                    label = { Text("My files") },
                    selected = selectedItem == "My files",
                    onClick = {
                        selectedItem = "My files"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Group, contentDescription = null) },
                    label = { Text("Shared with me") },
                    selected = selectedItem == "Shared with me",
                    onClick = {
                        selectedItem = "Shared with me"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.AccessTime, contentDescription = null) },
                    label = { Text("Recent") },
                    selected = selectedItem == "Recent",
                    onClick = {
                        selectedItem = "Recent"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Delete, contentDescription = null) },
                    label = { Text("Trash") },
                    selected = selectedItem == "Trash",
                    onClick = {
                        selectedItem = "Trash"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                // Subtitle / Family section
                Text(
                    text = "Categories",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Save, contentDescription = null) },
                    label = { Text("Family") },
                    selected = selectedItem == "Family",
                    onClick = {
                        selectedItem = "Family"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.home_screen)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú de hamburguesa",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { openWebScreen(context)}) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Marcar como favorito",
                                tint = Color.White
                            )

                        }
                        IconButton(onClick = { openSecondActivity(context) }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Compartir",
                                tint = Color.White
                            )
                        }
                        DropDownMenu()
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Gray,
                        titleContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Gray,
                    contentColor = Color.White,
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.bottom_app_bar),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(fabMessage)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = fabMessage
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            ContentView(modifier = Modifier.padding(innerPadding))
        }
    }
}

fun openWebScreen(context: Context){
    try {
        val url = Uri.parse("https://web.whatsapp.com/")
        val intent = Intent(Intent.ACTION_VIEW, url)
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("MainActivity", "Error opening web screen", e)
    }
}

// Abre el teclado de marcado del teléfono (el usuario decide si llama)
fun openDialer(context: Context) {
    try {
        val intent = Intent(Intent.ACTION_DIAL)
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("MainActivity", "Error opening dialer", e)
    }
}

// Abre la pantalla de ajustes de Bluetooth
fun openBluetoothSettings(context: Context) {
    try {
        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("MainActivity", "Error opening bluetooth settings", e)
    }
}

// Abre la lista de contactos
fun openContacts(context: Context) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI)
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("MainActivity", "Error opening contacts", e)
    }
}

// Abre la SecondActivity (otra pantalla de la app)
fun openSecondActivity(context: Context) {
    try {
        val intent = Intent(context, SecondActivity::class.java)
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("MainActivity", "Error opening SecondActivity", e)
    }
}