/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sun.enterprise.security;

import org.glassfish.external.statistics.CountStatistic;
import org.glassfish.external.statistics.TimeStatistic;
import org.glassfish.external.statistics.impl.CountStatisticImpl;
import org.glassfish.external.statistics.impl.TimeStatisticImpl;
import org.glassfish.gmbal.Description;
import org.glassfish.gmbal.AMXMetadata;
import org.glassfish.gmbal.ManagedAttribute;
import org.glassfish.gmbal.ManagedObject;
import org.glassfish.external.probe.provider.annotations.ProbeListener;
import org.glassfish.external.probe.provider.annotations.ProbeParam;

/**
 *
 * @author nithyasubramanian
 */
@AMXMetadata(type="web-security-deployer-mon", group="monitoring", isSingleton=false)
@ManagedObject
@Description( "Web application Security Deployment statistics" )
public class WebSecurityDeployerStatsProvider {

    TimeStatisticImpl deploymentTime = new TimeStatisticImpl(0, 0, 0, 0, "DeploymentTime", "milliseconds", "Deployment Time", 0, 0);

    TimeStatisticImpl generationTime = new TimeStatisticImpl(0, 0, 0, 0, "GenerationTime", "milliseconds", "Generation Time", 0, 0);

    TimeStatisticImpl undeploymentTime = new TimeStatisticImpl(0, 0, 0, 0, "UndeploymentTime", "milliseconds", "Undeployment Time", 0, 0);

    TimeStatisticImpl removalTime = new TimeStatisticImpl(0, 0, 0, 0, "RemovalTime", "milliseconds", "Removal Time", 0, 0);

    CountStatisticImpl secMgrCount = new CountStatisticImpl("WebSecurityManagerCount", "count", "No of Web security managers");

    CountStatisticImpl policyConfCount= new CountStatisticImpl("WebPolicyConfigurationCount", "count", "No of Policy Configuration Objects");

    @ManagedAttribute(id="depolymenttime")
    public TimeStatistic getDeploymentTime() {
        return deploymentTime.getStatistic();
    }

    @ManagedAttribute(id="generationtime")
    public TimeStatistic getGenerationTime() {
        return generationTime.getStatistic();
    }

    @ManagedAttribute(id="undepolymenttime")
    public TimeStatistic getUndeploymentTime() {
        return undeploymentTime.getStatistic();
    }


    @ManagedAttribute(id="removaltime")
    public TimeStatistic getRemovalTime() {
        return removalTime.getStatistic();
    }

    @ManagedAttribute(id="websecuritymanagercount")
    public CountStatistic getWebSMCount() {
        return secMgrCount.getStatistic();

    }

    @ManagedAttribute(id="webpolicyconfigurationcount")
    public CountStatistic getPCCount() {
        return policyConfCount.getStatistic();
    }

    @ProbeListener("glassfish:core:web:webDeploymentStartedEvent")
    public void webDeploymentStartedEvent(@ProbeParam("appName")String appName){
       deploymentTime.setStartTime(System.currentTimeMillis());
    }

    @ProbeListener("glassfish:core:web:webDeploymentEndedEvent")
    public void webDeploymentEndedEvent(@ProbeParam("appName")String appName){

    }

    @ProbeListener("glassfish:core:web:securityManagerCreationEvent")
    public void securityManagerCreationEvent(
            @ProbeParam("appName") String appName) {
        secMgrCount.increment();
    }

    @ProbeListener("glassfish:core:web:securityManagerDestructionEvent")
    public void securityManagerDestructionEvent(
            @ProbeParam("appName") String appName) {
        secMgrCount.decrement();
    }

    @ProbeListener("glassfish:core:web:policyConfigurationCreationEvent")
    public void policyConfirationCreationEvent(
            @ProbeParam("contextId") String contextId) {
        policyConfCount.increment();
    }

    @ProbeListener("glassfish:core:web:policyConfigurationDestructionEvent")
    public void policyConfigurationDestructionEvent(
            @ProbeParam("contextId") String contextId) {
        policyConfCount.decrement();
    }





}
