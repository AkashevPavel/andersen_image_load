package com.akashevpavel.downloadimage



import android.app.DownloadManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.IOException
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {
    private lateinit var mImageView: ImageView
    private lateinit var mDownloadLinkEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mImageView = findViewById(R.id.load_imageview)
        mDownloadLinkEditText = findViewById(R.id.link_edittext)





    }

    fun downloadPicture(view: View) {

        Glide
            .with(this)
            .load(mDownloadLinkEditText.text.toString())
            .centerCrop()
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    showToast()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            })
            .into(mImageView)

    }

    fun oldwayDownload(view: View) {
        Thread(Runnable {
            kotlin.run {
                try {
                    mImageView.setImageBitmap(BitmapFactory
                        .decodeStream(URL(mDownloadLinkEditText.text.toString())
                            .openConnection().getInputStream()))
                } catch (e: IOException) {
                    showToast()
                }

            }

        }).start()

    }

    fun showToast() {
        runOnUiThread{
            Toast.makeText(this, R.string.connection_problems, Toast.LENGTH_SHORT).show()
        }
    }


}

