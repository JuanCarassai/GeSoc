package com.example.demoquartz;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import validadorDeCompras.ValidadorCompras;

public class JobImpl implements Job {

    private static int count = 0;
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        JobDetail jobDetail = jobContext.getJobDetail();
        count++;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("EJECUTANDO JOB " + jobDetail.getKey());
        System.out.println("Ejecucion N° " + count);
        System.out.println("Inicio: " + jobContext.getFireTime());
        System.out.println("Proxima ejecucion: " + jobContext.getNextFireTime());   
        ValidadorCompras.getInstance().validar();
        System.out.println("NO QUEDA NADA MÁS POR VALIDAR");
    }

}
