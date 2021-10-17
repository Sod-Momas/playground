package cc.momas.jol;

import org.openjdk.jol.samples.*;

/**
 * @author Sod-Momas
 * @since 2021/10/17
 */
public class Samples {
    public static void main(String[] args) throws Exception {
        JOLSample_01_Basic.main(null);
        JOLSample_02_Alignment.main(null);
        JOLSample_03_Packing.main(null);
        JOLSample_04_Inheritance.main(null);
        JOLSample_05_SuperGaps.main(null);
        JOLSample_06_HierarchyGaps.main(null);
        JOLSample_07_Exceptions.main(null);
        JOLSample_08_Class.main(null);
        JOLSample_09_Contended.main(null); // -XX:-RestrictContended
        JOLSample_10_DataModels.main(null);
        JOLSample_11_ClassWord.main(null);

        // -XX:BiasedLockingStartupDelay=0 on JDK 8
        //  After JDK 15 -XX:+UseBiasedLocking
        JOLSample_12_BiasedLocking.main(null);
        JOLSample_13_ThinLocking.main(null); // On modern JDKs, starting with 9 run with -XX:-UseBiasedLocking
        JOLSample_14_FatLocking.main(null);
        JOLSample_15_IdentityHashCode.main(null);
        JOLSample_16_IHC_BL_Conflict.main(null); //On JDK 15+, this test should enable -XX:+UseBiasedLocking.
        JOLSample_17_ArrayLength.main(null);
        JOLSample_18_ArrayAlignment.main(null);
        JOLSample_19_AL_LL.main(null);
        JOLSample_20_Allocation.main(null);
        JOLSample_21_Layouts.main(null);
        JOLSample_22_Promotion.main(null);
        JOLSample_23_Roots.main(null);
        JOLSample_24_Arrays.main(null);// -XX:ParallelGCThreads=1.
        JOLSample_25_Compaction.main(null); // -Xmx1g -XX:+UseParallelGC -XX:ParallelGCThreads=1
        JOLSample_26_Defragmentation.main(null);// -Xmx1g -XX:+UseParallelGC -XX:ParallelGCThreads=1
        JOLSample_27_Colocation.main(null);// -Xmx1g -XX:+UseParallelGC -XX:ParallelGCThreads=1
        JOLSample_28_Difference.main(null);
    }
}
