package com.simpletorch.simpletorch; 


import android.os.Bundle; 
import android.app.Activity; 
import android.view.View; 
import android.view.View.OnClickListener; 
import android.widget.Button; 
import android.widget.ImageView;
import android.hardware.Camera; 
import android.hardware.Camera.Parameters; 
import android.widget.Toast; 
import java.util.List; 

import com.searchboxsdk.android.StartAppSearch;
import com.startapp.android.publish.StartAppAd;





public class MainActivity extends Activity 
{ 
    
	private Boolean onoff=false;
    private Camera dispCamara; 
    Parameters parametrosCamara; 
    private ImageView imagen;

    @Override 
    public void onCreate(Bundle savedInstanceState) 
    { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main); 
        imagen=(ImageView)findViewById(R.id.imagen);
      
    }
    
    
    //Al cerrar la aplicación apagar el flash 
    public void finish() 
    { 
       if (dispCamara != null) 
        { 
           dispCamara.release(); 
           dispCamara = null; 
        } 
        super.finish(); 
    } 
    
    @Override 
    protected void onResume() 
    {        
        super.onResume(); 
        try 
        { 
           dispCamara = Camera.open(); 
        } 
        catch( Exception e ) 
        { 
          
        }        
    }    
    
    
    public void onoff(View view){
    	
    	if (onoff==false){
    		imagen.setImageResource(R.drawable.encendido);
    		
    		if (dispCamara != null) 
            { 
            

               Parameters parametrosCamara = 
                  dispCamara.getParameters(); 
                
               
               List<String> modosFlash = 
                  parametrosCamara.getSupportedFlashModes (); 
                
               if (modosFlash != null && 
                     modosFlash.contains( 
                           Camera.Parameters.FLASH_MODE_TORCH)) 
               { 
                    
                 parametrosCamara.setFlashMode( 
                       Camera.Parameters.FLASH_MODE_TORCH); 
                 parametrosCamara.setFocusMode( 
                       Camera.Parameters.FOCUS_MODE_INFINITY); 
                
                 try 
                 { 
                      dispCamara.setParameters(parametrosCamara); 
                      dispCamara.startPreview(); 
                 } 
                 catch (Exception e) 
                 { 
                     Toast.makeText(getApplicationContext(), 
                             "Error al activar la linterna", 
                             Toast.LENGTH_SHORT).show(); 
                 } 
               } 
               else 
               { 
                   Toast.makeText(getApplicationContext(), 
                   "El dispositivo no tiene el modo de Flash Linterna", 
                      Toast.LENGTH_SHORT).show();               
               } 
            } 
            else 
            { 
               Toast.makeText(getApplicationContext(), 
                      "No se ha podido acceder al Flash de la cámara", 
                      Toast.LENGTH_SHORT).show(); 
            }
    		
    		
    		onoff=true;
        }    else if(onoff==true){
        	imagen.setImageResource(R.drawable.apagado);
        	
        	  if (dispCamara != null) 
              { 
                  parametrosCamara = dispCamara.getParameters(); 
                  parametrosCamara.setFlashMode(Parameters.FLASH_MODE_OFF); 
                  dispCamara.setParameters(parametrosCamara); 
              } 
              else 
              { 
                 Toast.makeText (getApplicationContext(), 
                        "No se ha podido acceder al Flash de la cámara", 
                        Toast.LENGTH_SHORT).show(); 
              } 
        	  
      		  
        	  onoff=false;
        	
        	
        }
    	
    	
    		
    	}
    	
    	

}