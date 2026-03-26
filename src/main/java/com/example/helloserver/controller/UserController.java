package com.example.helloserver.controller;

import com.example.helloserver.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // 1. 获取用户信息（查）
    @GetMapping("/{id}")
    }

    // 2. 新增用户（增）- 接收 JSON 格式数据
    @PostMapping
    }

    // 3. 全量更新用户信息（改）
    @PutMapping("/{id}")
    }

    // 4. 删除用户（删）
    @DeleteMapping("/{id}")
    }
}