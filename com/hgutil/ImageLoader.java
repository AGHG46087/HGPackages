/*
 * @author:			Owner
 * date:				Dec 9, 2003
 * Package:			com.hgutil
 * File Name:		ImageLoader.java
 */
package com.hgutil;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil<BR>
 * File Name:   ImageLoader.java<BR>
 * Type Name:   ImageLoader<BR>
 * Description: lass Loads an Image either as the normal Size or scaled.
 */
public class ImageLoader extends Component
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3257291326906119473L;
  /** Field <code>imageLoader</code> : ImageLoader */
  private static ImageLoader imageLoader;

  static 
  {
    imageLoader = new ImageLoader();
  }

  /**
   * Constructor for ImageLoader. 
   * 
   */
  private ImageLoader() {
    super();
  }

  /**
   * Method loadImage.   Loads an Image
   * @param imageName - String, the Complete Path of the Image
   * @return Image
   */
  public static Image loadImage(String imageName)
  {
    Image image = Toolkit.getDefaultToolkit().getImage(imageName);
    MediaTracker tracker = new MediaTracker(imageLoader);
    tracker.addImage(image,0);
    try
    {
      tracker.waitForID(0);
    }
    catch( InterruptedException exc )
    { // Swallow Exception
    }
    int status =  tracker.statusID(0,true);
    if ( status != MediaTracker.COMPLETE && status != MediaTracker.LOADING )
     {
      throw new RuntimeException( "Image: ["+imageName+"] has failed to Load" );
    }
    return image;
  }
  /**
   * Method loadScaledImage.  
   * @param imageName - String, the Complete Path of the Image
   * @param PERCENT - final int, the percentage of the scaling
   * @return Image
   */
  public static Image loadScaledImage(String imageName, final int PERCENT)
  {
    Image image = loadImage(imageName);
    int width = image.getWidth(imageLoader) * PERCENT / 100;
    int height = image.getHeight(imageLoader) * PERCENT / 100;
    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    MediaTracker tracker = new MediaTracker(imageLoader);
    tracker.addImage(image,0);
    try
    {
      tracker.wait(0);
    }
    catch( InterruptedException exc )
    { // Swallow Exception
    }
    int status =  tracker.statusID(0,true);
    if ( status != MediaTracker.COMPLETE && status != MediaTracker.LOADING )
    {
      throw new RuntimeException( "Image: ["+imageName+"] has failed to Load a Scaled Image" );
    }
    
    return scaledImage;
  }
}
