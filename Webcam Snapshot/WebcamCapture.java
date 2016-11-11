package com.cooltrickshome;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class WebcamCapture {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640, 480));
		webcam.open();
		ImageIO.write(webcam.getImage(), "PNG", new File("picture.png"));
		System.out.println("Snapshot taken from cam and stored at "+new File("").getAbsolutePath()+"\\picture.png");
	}

}
