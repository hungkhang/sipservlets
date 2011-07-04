/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.media;

import java.io.Serializable;

/**
 *
 * @author kulikov
 */
public class Frame implements Serializable {
    private byte[] data;
    private double duration;
    
    //carriers meta data about existing frame.
    private Object metaData;
    
    /**
     * Creates new instance of media frame.
     * 
     * @param data the media data.
     * @param duration the duration of media frame.
     */
    public Frame(byte[] data, double duration) {
        this.data = data;
        this.duration = duration;
    }
    
    /**
     * Gets the reference to MetaData object which describes this frame.
     * 
     * @return the meta data object or null if not asigned.
     */
    public Object getMediaData() {
        return metaData;
    }
    
    /**
     * Assigns meta data with this frame.
     * 
     * @param metaData the metadata object.
     */
    public void setMetaData(Object metaData) {
        this.metaData = metaData;
    }
    
    /**
     * Gets media data.
     * 
     * @return array of bytes which holds media data.
     */
    public byte[] getData() {
        return data;
    }
    
    /**
     * Modifies media data associated with this frame.
     * 
     * @param data the media data.
     */
    public void setData(byte[] data) {
        this.data = data;
    }
    
    /**
     * Gets duration of this frame.
     * 
     * @return the duration in milliseconds.
     */
    public double getDuration() {
        return duration;
    }
}