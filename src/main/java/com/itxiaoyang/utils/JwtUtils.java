package com.itxiaoyang.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 */
public class JwtUtils {

    /**
     * JWT 加密使用的密钥（建议在生产环境中从配置文件或环境变量中读取）
     */
    private static final String SECRET_KEY = "aXR4aWFveWFuZw==";

    /**
     * JWT 令牌过期时间：12 小时（单位：毫秒）
     */
    private static final long EXPIRATION = 12 * 3600 * 1000L; // 12小时

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义载荷数据（如用户ID、用户名等）
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)//指定加密算法和密钥
                .addClaims(claims)//添加自定义载荷数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))//设置令牌过期时间
                .compact();//生成令牌
    }

    /**
     * 解析 JWT 令牌
     *
     * @param token 要解析的 JWT 字符串
     * @return 解析后的 Claims 对象，包含载荷信息
     * @throws RuntimeException 如果 token 无效或已过期
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)//指定密钥
                    .parseClaimsJws(token)//解析令牌
                    .getBody();//获取自定义载荷数据
        } catch (Exception e) {
            throw new RuntimeException("无效或已过期的 JWT 令牌", e);
        }
    }
}