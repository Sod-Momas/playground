package cc.momas.jol;

import org.openjdk.jol.samples.*;

/**
 * @author Sod-Momas
 * @since 2021/10/17
 */
public class Samples {
    public static void main(String[] args) throws Exception {
        JOLSample_01_Basic.main(args);
        JOLSample_02_Alignment.main(args);
        JOLSample_03_Packing.main(args);
        JOLSample_04_Inheritance.main(args);
        JOLSample_05_SuperGaps.main(args);
        JOLSample_06_HierarchyGaps.main(args);
        JOLSample_07_Exceptions.main(args);
        JOLSample_08_Class.main(args);
        JOLSample_09_Contended.main(args); // -XX:-RestrictContended
        JOLSample_10_DataModels.main(args);
        JOLSample_11_ClassWord.main(args);

        // -XX:BiasedLockingStartupDelay=0 on JDK 8
        //  After JDK 15 -XX:+UseBiasedLocking
        JOLSample_12_BiasedLocking.main(args);
        JOLSample_13_ThinLocking.main(args); // On modern JDKs, starting with 9 run with -XX:-UseBiasedLocking
        JOLSample_14_FatLocking.main(args);
        JOLSample_15_IdentityHashCode.main(args);
        JOLSample_16_IHC_BL_Conflict.main(args); //On JDK 15+, this test should enable -XX:+UseBiasedLocking.
        JOLSample_17_ArrayLength.main(args);
        JOLSample_18_ArrayAlignment.main(args);
        JOLSample_19_AL_LL.main(args);
        JOLSample_20_Allocation.main(args);
        JOLSample_21_Layouts.main(args);
        JOLSample_22_Promotion.main(args);
        JOLSample_23_Roots.main(args);
        JOLSample_24_Arrays.main(args);// -XX:ParallelGCThreads=1.
        JOLSample_25_Compaction.main(args); // -Xmx1g -XX:+UseParallelGC -XX:ParallelGCThreads=1
        JOLSample_26_Defragmentation.main(args);// -Xmx1g -XX:+UseParallelGC -XX:ParallelGCThreads=1
        JOLSample_27_Colocation.main(args);// -Xmx1g -XX:+UseParallelGC -XX:ParallelGCThreads=1
        JOLSample_28_Difference.main(args);
    }
}
