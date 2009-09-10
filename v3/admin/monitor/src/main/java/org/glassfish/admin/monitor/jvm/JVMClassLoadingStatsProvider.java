/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 *
 * Contributor(s):
 *
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.admin.monitor.jvm;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import org.glassfish.external.statistics.CountStatistic;
import org.glassfish.external.statistics.impl.CountStatisticImpl;
import org.glassfish.gmbal.AMXMetadata;
import org.glassfish.gmbal.Description;
import org.glassfish.gmbal.ManagedAttribute;
import org.glassfish.gmbal.ManagedObject;

/* jvm.class-loading-system */
// v2: com.sun.appserv:name=class-loading-system,type=class-loading-system,category=monitor,server=server
// v3: 
@AMXMetadata(type="class-loading-system-mon", group="monitoring")
@ManagedObject
@Description( "JVM Class Loading Statistics" )
public class JVMClassLoadingStatsProvider {

    private ClassLoadingMXBean clBean = ManagementFactory.getClassLoadingMXBean();

    private CountStatisticImpl loadedClassCount = new CountStatisticImpl("LoadedClassCount", CountStatisticImpl.UNIT_COUNT,
            "Number of classes currently loaded in the Java virtual machine");
    private CountStatisticImpl totalLoadedClassCount = new CountStatisticImpl("TotalLoadedClassCount", CountStatisticImpl.UNIT_COUNT,
            "Total number of classes that have been loaded since the Java virtual machine has started execution");
    private CountStatisticImpl unloadedClassCount = new CountStatisticImpl("UnLoadedClassCount", CountStatisticImpl.UNIT_COUNT,
            "Total number of classes unloaded since the Java virtual machine has started execution");

    @ManagedAttribute(id="loadedclass-count")
    @Description( "number of classes currently loaded in the JVM" )
    public CountStatistic getLoadedClassCount() {
        loadedClassCount.setCount(clBean.getLoadedClassCount());
        loadedClassCount.setLastSampleTime(System.currentTimeMillis());
        return loadedClassCount;
    }

    @ManagedAttribute(id="totalloadedclass-count")
    @Description( "total number of classes loaded since the JVM started" )
    public CountStatistic getTotalLoadedClassCount() {
        totalLoadedClassCount.setCount(clBean.getTotalLoadedClassCount());
        totalLoadedClassCount.setLastSampleTime(System.currentTimeMillis());
        return totalLoadedClassCount;
    }

    @ManagedAttribute(id="unloadedclass-count")
    @Description( "total number of classes unloaded since the JVM started" )
    public CountStatistic getUnloadedClassCount() {
        unloadedClassCount.setCount(clBean.getUnloadedClassCount());
        unloadedClassCount.setLastSampleTime(System.currentTimeMillis());
        return unloadedClassCount;
    }
}