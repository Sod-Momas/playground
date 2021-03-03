package cc.momas.dlock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
@RestController
public class DLockController {

    @RequestMapping("/lock")
    public String executeBusiness(@RequestParam("bizName") String bizName,
                                  @RequestParam(value = "remark", required = false) String remark) {
        System.out.println(Thread.currentThread().getName());
        if (DataSources.tryLock(bizName, remark)) {
            try {
                executeBiz();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DataSources.releaseLock(bizName);
            }
            return "执行完成";
        } else {
            return "获取锁失败";
        }
    }

    @RequestMapping("/clean")
    public String clean() {
        DataSources.cleanLock();
        return "清理完成";
    }

    private void executeBiz() throws InterruptedException {
        // 假装执行某业务需要这段时间
//        TimeUnit.SECONDS.sleep(5);
        Thread.sleep(2000);
//        for (int i = 0; i < 100000; i++) {
//            UUID.randomUUID().toString();
//        }
    }
}
