package ru.example.a05122024

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.example.a05122024.databinding.ActivityMainBinding

class BaseViewModel:ViewModel() {
    var activity: AppCompatActivity? = null
    var  binding:ActivityMainBinding? = null
    var launcher: ActivityResultLauncher<Intent>? = null
    var launcher2:ActivityResultLauncher<PickVisualMediaRequest>? = null
    fun initBase(activity:AppCompatActivity, binding: ActivityMainBinding){
        this.activity = activity
        this.binding = binding
        launcher =  activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                val uriImg = it.data?.data
                binding.img.setImageURI(uriImg)
            }
        )
        launcher2 = activity?.registerForActivityResult(
            ActivityResultContracts.PickVisualMedia(),
            ActivityResultCallback {
                var  uriImg = it
                binding.img.setImageURI(uriImg)
            }
        )
    }
    fun clickNumber(){
        val uri = Uri.parse("tel:88005553535")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        activity?.startActivity(intent)
    }
    fun ClickUrl(){
        val uri = Uri.parse("https://www.mirea.ru")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        activity?.startActivity(intent)
    }
    fun ClickEmail(){
        val addresses = arrayOf("albisharafikova@yandex.ru", "mail@mail.ru", "yandex@yandex.ru")
        val uri = Uri.parse("mailto:")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        //адреса для отправки
        //Заголовок
        //Тело письма
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Заголовок")
        intent.putExtra(Intent.EXTRA_TEXT, "Это основное тело письма")
        activity?.startActivity(intent)
    }

    fun ChooseImage(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        launcher2?.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly))

    }

}