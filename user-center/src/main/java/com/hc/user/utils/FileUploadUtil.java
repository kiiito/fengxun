package com.hc.user.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传工具类
 */
@Slf4j
public class FileUploadUtil {

    /**
     * 上传头像文件
     * @param file 上传的文件
     * @param uploadPath 上传目录路径
     * @return 文件名（不包含路径）
     * @throws IOException 文件操作异常
     */
    public static String uploadAvatar(MultipartFile file, String uploadPath) throws IOException {
        // 1. 基本验证
        if (file == null || file.isEmpty()) {
            throw new IOException("文件不能为空");
        }

        // 2. 验证文件类型
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.trim().isEmpty()) {
            throw new IOException("文件名不能为空");
        }

        // 3. 获取文件扩展名
        String fileExtension = getFileExtension(originalFileName);
        if (!isValidImageExtension(fileExtension)) {
            throw new IOException("不支持的文件格式: " + fileExtension);
        }

        // 4. 确保上传目录存在
        File destDir = new File(uploadPath);
        if (!destDir.exists()) {
            boolean created = destDir.mkdirs();
            if (!created) {
                throw new IOException("创建目录失败: " + uploadPath);
            }
            log.info("创建头像存储目录: {}", uploadPath);
        }

        // 5. 检查目录写入权限
        if (!destDir.canWrite()) {
            throw new IOException("目录没有写入权限: " + uploadPath);
        }

        // 6. 生成唯一文件名
        String fileName = generateUniqueFileName(fileExtension);

        // 7. 保存文件
        File destFile = new File(destDir, fileName);

        // 安全处理：先保存到临时文件，再重命名（可选）
        file.transferTo(destFile);

        // 8. 验证文件是否保存成功
        if (!destFile.exists() || destFile.length() == 0) {
            throw new IOException("文件保存失败: " + destFile.getAbsolutePath());
        }

        log.info("头像文件上传成功: {} -> {}", originalFileName, destFile.getAbsolutePath());
        return fileName;
    }

    /**
     * 获取文件扩展名
     * @param fileName 文件名
     * @return 文件扩展名（包含点，如 .jpg）
     */
    private static String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return ".jpg"; // 默认扩展名
        }
        return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    }

    /**
     * 验证图片文件扩展名
     * @param extension 文件扩展名
     * @return 是否有效
     */
    private static boolean isValidImageExtension(String extension) {
        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif", ".webp", ".bmp"};
        for (String allowedExt : allowedExtensions) {
            if (allowedExt.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成唯一文件名
     * @param fileExtension 文件扩展名
     * @return 唯一文件名
     */
    private static String generateUniqueFileName(String fileExtension) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStr = sdf.format(new Date());
        String randomStr = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return "avatar_" + timeStr + "_" + randomStr + fileExtension;
    }

    /**
     * 验证文件类型（基于Content-Type）
     * @param contentType 文件类型
     * @param allowedTypes 允许的类型列表（逗号分隔）
     * @return 是否有效
     */
    public static boolean isValidImageType(String contentType, String allowedTypes) {
        if (contentType == null || allowedTypes == null) {
            return false;
        }

        String[] allowedTypeArray = allowedTypes.split(",");
        for (String allowedType : allowedTypeArray) {
            if (contentType.startsWith(allowedType.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除文件
     * @param filePath 文件完整路径
     * @return 是否删除成功
     */
    public static boolean deleteFile(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            return false;
        }

        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            boolean deleted = file.delete();
            if (deleted) {
                log.info("文件删除成功: {}", filePath);
            } else {
                log.warn("文件删除失败: {}", filePath);
            }
            return deleted;
        }
        return false;
    }

    /**
     * 删除指定目录下的文件（根据文件名）
     * @param directory 目录路径
     * @param fileName 文件名
     * @return 是否删除成功
     */
    public static boolean deleteFileFromDirectory(String directory, String fileName) {
        if (directory == null || fileName == null) {
            return false;
        }

        File file = new File(directory, fileName);
        return deleteFile(file.getAbsolutePath());
    }

    /**
     * 获取文件大小（MB）
     * @param file 文件
     * @return 文件大小（MB）
     */
    public static double getFileSizeMB(MultipartFile file) {
        if (file == null) {
            return 0;
        }
        return file.getSize() / (1024.0 * 1024.0);
    }

    /**
     * 检查文件大小是否超过限制
     * @param file 文件
     * @param maxSizeMB 最大大小（MB）
     * @return 是否超过限制
     */
    public static boolean isFileSizeExceeded(MultipartFile file, double maxSizeMB) {
        return getFileSizeMB(file) > maxSizeMB;
    }

    /**
     * 获取文件MIME类型
     * @param file 文件
     * @return MIME类型
     */
    public static String getFileMimeType(MultipartFile file) {
        return file != null ? file.getContentType() : null;
    }

    /**
     * 清理上传目录中的旧文件（可选功能）
     * @param directory 目录路径
     * @param daysBefore 几天前的文件
     * @return 删除的文件数量
     */
    public static int cleanupOldFiles(String directory, int daysBefore) {
        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            return 0;
        }

        long cutoffTime = System.currentTimeMillis() - (daysBefore * 24L * 60 * 60 * 1000);
        int deletedCount = 0;

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.lastModified() < cutoffTime) {
                    if (file.delete()) {
                        deletedCount++;
                        log.info("删除旧文件: {}", file.getName());
                    }
                }
            }
        }

        log.info("清理完成，删除了 {} 个旧文件", deletedCount);
        return deletedCount;
    }
}