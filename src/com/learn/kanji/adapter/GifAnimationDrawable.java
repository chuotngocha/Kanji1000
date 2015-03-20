package com.learn.kanji.adapter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;

public class GifAnimationDrawable extends AnimationDrawable {
	private boolean decoded;

	private GifDecoder mGifDecoder;

	protected Bitmap mTmpBitmap;

	private int height, width;

	private Resources resource;

	public GifAnimationDrawable(File f, Resources resource) throws IOException {
		this(f, false, resource);
		this.resource = resource;
	}

	public GifAnimationDrawable(InputStream is, Resources resource)
			throws IOException {
		this(is, false, resource);
		this.resource = resource;

	}

	public GifAnimationDrawable(File f, boolean inline, Resources resource)
			throws IOException {
		this(new BufferedInputStream(new FileInputStream(f), 32768), inline,
				resource);
		this.resource = resource;
	}

	public GifAnimationDrawable(InputStream is, boolean inline,
			Resources resource) throws IOException {
		super();
		this.resource = resource;
		InputStream bis = is;
		if (!BufferedInputStream.class.isInstance(bis))
			bis = new BufferedInputStream(is, 32768);
		decoded = false;
		mGifDecoder = new GifDecoder();
		mGifDecoder.read(bis);
		mTmpBitmap = mGifDecoder.getFrame(0);
		height = mTmpBitmap.getHeight();
		width = mTmpBitmap.getWidth();
		addFrame(new BitmapDrawable(resource, mTmpBitmap),
				mGifDecoder.getDelay(0));
		setOneShot(mGifDecoder.getLoopCount() != 0);
		setVisible(true, true);
		if (inline) {
			loader.run();
		} else {
			new Thread(loader).start();
		}
	}

	public boolean isDecoded() {
		return decoded;
	}

	private Runnable loader = new Runnable() {
		public void run() {
			mGifDecoder.complete();
			int i, n = mGifDecoder.getFrameCount(), t;
			for (i = 1; i < n; i++) {
				mTmpBitmap = mGifDecoder.getFrame(i);
				t = mGifDecoder.getDelay(i);
				addFrame(new BitmapDrawable(resource, mTmpBitmap), t);
			}
			decoded = true;
			mGifDecoder = null;
		}
	};

	public int getMinimumHeight() {
		return height;
	}

	public int getMinimumWidth() {
		return width;
	}

	public int getIntrinsicHeight() {
		return height;
	}

	public int getIntrinsicWidth() {
		return width;
	}

}