package com.mralmost.community.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.GenerateObjectPrivateUrlApi;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Lxj
 * @Package com.mralmost.community.provider
 * @Description TODO
 * @date: 2020/2/24
 */
@Service
public class UCloudProvider {

    @Value("${ucloud.ufile.public-key}")
    private String publicKey;

    @Value("${ucloud.ufile.private-key}")
    private String privateKey;

    @Value("${ucloud.ufile.bucket-name}")
    private String bucketName;

    @Value("${ucloud.ufile.region}")
    private String region;

    @Value("${ucloud.ufile.suffix}")
    private String suffix;

    @Value("${ucloud.ufile.expires}")
    private Integer expires;


    public String upload(InputStream fileStream, String mimeType, String fileName) {
        //图片名称使用UUID.后缀名
        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
            return null;
        }

        try {
            // 对象相关API的授权器
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(
                    publicKey, privateKey);
            /**
             * 您也可以使用已登记的自定义域名
             * 注意'http://www.your_domain.com'指向的是某个特定的bucket+region+域名后缀，
             * eg：http://www.your_domain.com -> www.your_bucket.bucket_region.ufileos.com
             */
            ObjectConfig config = new ObjectConfig(region, suffix);
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(generatedFileName)
                    .toBucket(bucketName)
                    .setOnProgressListener((bytesWritten, contentLength) -> {
                    })
                    .execute();

            if (response != null && response.getRetCode() == 0) {
                String url = UfileClient.object(objectAuthorization, config)
                        .getDownloadUrlFromPrivateBucket(generatedFileName, bucketName, expires).createUrl();
                return url;
            } else {
                throw new CustomException(ErrorCode.FILE_UPLOAD_FAIL);
            }
        } catch (UfileClientException e) {
            throw new CustomException(ErrorCode.FILE_UPLOAD_FAIL);
        } catch (UfileServerException e) {
            throw new CustomException(ErrorCode.FILE_UPLOAD_FAIL);
        }
    }

}
