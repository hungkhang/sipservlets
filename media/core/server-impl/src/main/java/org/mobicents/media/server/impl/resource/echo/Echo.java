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

package org.mobicents.media.server.impl.resource.echo;

import java.util.ArrayList;
import java.util.Collection;
import org.mobicents.media.MediaSink;
import org.mobicents.media.MediaSource;
import org.mobicents.media.server.impl.BaseComponent;
import org.mobicents.media.server.impl.resource.Proxy;
import org.mobicents.media.server.spi.MediaType;
import org.mobicents.media.server.spi.ResourceGroup;

/**
 *
 * @author kulikov
 */
public class Echo extends BaseComponent implements ResourceGroup {

    private Proxy audioProxy;
    private Proxy videoProxy;
    private static ArrayList<MediaType> mediaTypes = new ArrayList();
    static {
        mediaTypes.add(MediaType.AUDIO);
        mediaTypes.add(MediaType.VIDEO);
    }
    
    public Echo(String name) {
        super(name);
        audioProxy = new Proxy(name + ".proxy.audio");
        videoProxy = new Proxy(name + ".proxy.video");
    }
    
    public Collection<MediaType> getMediaTypes() {
        return mediaTypes;
    }
    
    public MediaSink getSink(MediaType media) {
        return media == MediaType.AUDIO ? audioProxy.getInput() : videoProxy.getInput();
    }

    public MediaSource getSource(MediaType media) {
        return media == MediaType.AUDIO ? audioProxy.getOutput() : videoProxy.getOutput();
    }


    public void start() {
        audioProxy.start();
        videoProxy.start();
    }

    public void stop() {
        audioProxy.stop();
        videoProxy.stop();
    }

}