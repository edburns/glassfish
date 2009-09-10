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

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import org.glassfish.external.statistics.CountStatistic;
import org.glassfish.external.statistics.impl.CountStatisticImpl;
import org.glassfish.external.statistics.StringStatistic;
import org.glassfish.external.statistics.impl.StringStatisticImpl;
import org.glassfish.gmbal.AMXMetadata;
import org.glassfish.gmbal.Description;
import org.glassfish.gmbal.ManagedAttribute;
import org.glassfish.gmbal.ManagedObject;

/* jvm.runtime */
// v2 mbean: com.sun.appserv:name=runtime,type=runtime,category=monitor,server=server
// v3 mbean:
@AMXMetadata(type="runtime-mon", group="monitoring")
@ManagedObject
@Description( "JVM Runtime Statistics" )
public class JVMRuntimeStatsProvider {
    
    private RuntimeMXBean rtBean = ManagementFactory.getRuntimeMXBean();

    private StringStatisticImpl bootClassPath = new StringStatisticImpl("BootClassPath", "String",
                "Boot class path that is used by the bootstrap class loader to search for class files" );
    private StringStatisticImpl classPath = new StringStatisticImpl("ClassPath", "String",
                "Java class path that is used by the system class loader to search for class files" );
    private StringStatisticImpl inputArguments = new StringStatisticImpl("InputArguments", "String",
                "Input arguments passed to the Java virtual machine which does not include the arguments to the main method" );
    private StringStatisticImpl libraryPath = new StringStatisticImpl("LibraryPath", "String",
                "Java library path" );
    private StringStatisticImpl mgmtSpecVersion = new StringStatisticImpl("ManagementSpecVersion", "String",
                "Version of the specification for the management interface implemented by the running Java virtual machine" );
    private StringStatisticImpl runtimeName = new StringStatisticImpl("Name", "String",
                "Name representing the running Java virtual machine" );
    private StringStatisticImpl specName = new StringStatisticImpl("SpecName", "String",
                "Java virtual machine specification name" );
    private StringStatisticImpl specVendor = new StringStatisticImpl("SpecVendor", "String",
                "Java virtual machine specification vendor" );
    private StringStatisticImpl specVersion = new StringStatisticImpl("SpecVersion", "String",
                "Java virtual machine specification version" );
    private CountStatisticImpl uptime = new CountStatisticImpl("Uptime", CountStatisticImpl.UNIT_MILLISECOND,
            "Uptime of the Java virtual machine in milliseconds");
    private StringStatisticImpl vmName = new StringStatisticImpl("VmName", "String",
                "Java virtual machine implementation name" );
    private StringStatisticImpl vmVendor = new StringStatisticImpl("VmVendor", "String",
                "Java virtual machine implementation vendor" );
    private StringStatisticImpl vmVersion = new StringStatisticImpl("VmVersion", "String",
                "Java virtual machine implementation version" );

    @ManagedAttribute(id="bootclasspath-current")
    @Description( "boot class path that is used by the bootstrap class loader to search for class files" )
    public StringStatistic getBootClassPath() {
        bootClassPath.setCurrent(rtBean.getBootClassPath());
        bootClassPath.setStartTime(rtBean.getStartTime());
        bootClassPath.setLastSampleTime(System.currentTimeMillis());
        return bootClassPath;
    }

    @ManagedAttribute(id="classpath-current")
    @Description( "Java class path that is used by the system class loader to search for class files" )
    public StringStatistic getClassPath() {
        classPath.setCurrent(rtBean.getClassPath());
        classPath.setStartTime(rtBean.getStartTime());
        classPath.setLastSampleTime(System.currentTimeMillis());
        return classPath;
    }

    @ManagedAttribute(id="inputarguments-current")
    @Description( "input arguments passed to the Java virtual machine which does not include the arguments to the main method" )
    public StringStatistic getInputArguments() {
        List<String> inputList = rtBean.getInputArguments();
        StringBuffer sb = new StringBuffer();
        for (String arg : inputList) {
            sb.append(arg);
            sb.append(", ");
        }
        String finalString = sb.substring(0, sb.lastIndexOf(","));
        inputArguments.setCurrent(finalString);
        inputArguments.setStartTime(rtBean.getStartTime());
        inputArguments.setLastSampleTime(System.currentTimeMillis());
        return inputArguments;
    }

    @ManagedAttribute(id="librarypath-current")
    @Description( "Java library path" )
    public StringStatistic getLibraryPath() {
        libraryPath.setCurrent(rtBean.getLibraryPath());
        libraryPath.setStartTime(rtBean.getStartTime());
        libraryPath.setLastSampleTime(System.currentTimeMillis());
        return libraryPath;
    }

    @ManagedAttribute(id="managementspecversion-current")
    @Description( "version of the specification for the management interface implemented by the running Java virtual machine" )
    public StringStatistic getManagementSpecVersion() {
        mgmtSpecVersion.setCurrent(rtBean.getManagementSpecVersion());
        mgmtSpecVersion.setStartTime(rtBean.getStartTime());
        mgmtSpecVersion.setLastSampleTime(System.currentTimeMillis());
        return mgmtSpecVersion;
    }

    @ManagedAttribute(id="name-current")
    @Description( "name representing the running Java virtual machine" )
    public StringStatistic getRuntimeName() {
        runtimeName.setCurrent(rtBean.getName());
        runtimeName.setStartTime(rtBean.getStartTime());
        runtimeName.setLastSampleTime(System.currentTimeMillis());
        return runtimeName;
    }

    @ManagedAttribute(id="specname-current")
    @Description( "Java virtual machine specification name" )
    public StringStatistic getSpecName() {
        specName.setCurrent(rtBean.getSpecName());
        specName.setStartTime(rtBean.getStartTime());
        specName.setLastSampleTime(System.currentTimeMillis());
        return specName;
    }

    @ManagedAttribute(id="specvendor-current")
    @Description( "Java virtual machine specification vendor" )
    public StringStatistic getSpecVendor() {
        specVendor.setCurrent(rtBean.getSpecVendor());
        specVendor.setStartTime(rtBean.getStartTime());
        specVendor.setLastSampleTime(System.currentTimeMillis());
        return specVendor;
    }

    @ManagedAttribute(id="specversion-current")
    @Description( "Java virtual machine specification version" )
    public StringStatistic getSpecVersion() {
        specVersion.setCurrent(rtBean.getSpecVersion());
        specVersion.setStartTime(rtBean.getStartTime());
        specVersion.setLastSampleTime(System.currentTimeMillis());
        return specVersion;
    }

    @ManagedAttribute(id="uptime-count")
    @Description( "uptime of the Java virtual machine in milliseconds" )
    public CountStatistic getUptime() {
        uptime.setCount(rtBean.getUptime());
        uptime.setStartTime(rtBean.getStartTime());
        uptime.setLastSampleTime(System.currentTimeMillis());
        return uptime;
    }

    @ManagedAttribute(id="vmname-current")
    @Description( "Java virtual machine implementation name" )
    public StringStatistic getVmName() {
        vmName.setCurrent(rtBean.getVmName());
        vmName.setStartTime(rtBean.getStartTime());
        vmName.setLastSampleTime(System.currentTimeMillis());
        return vmName;
    }

    @ManagedAttribute(id="vmvendor-current")
    @Description( "Java virtual machine implementation vendor" )
    public StringStatistic getVmVendor() {
        vmVendor.setCurrent(rtBean.getVmVendor());
        vmVendor.setStartTime(rtBean.getStartTime());
        vmVendor.setLastSampleTime(System.currentTimeMillis());
        return vmVendor;
    }

    @ManagedAttribute(id="vmversion-current")
    @Description( "Java virtual machine implementation version" )
    public StringStatistic getVmVersion() {
        vmVersion.setCurrent(rtBean.getVmVersion());
        vmVersion.setStartTime(rtBean.getStartTime());
        vmVersion.setLastSampleTime(System.currentTimeMillis());
        return vmVersion;
    }   
}