package com.dwproject.controller;

import com.dwproject.entity.Resource;
import com.dwproject.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "*")
public class ResourceController {

    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping
    public List<Resource> getAllResources() {
        return resourceRepository.findByOrderByUploadedAtDesc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        return resourceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public List<Resource> getResourcesByType(@PathVariable String type) {
        return resourceRepository.findByType(type);
    }

    @PostMapping
    public Resource createResource(@RequestBody Resource resource) {
        return resourceRepository.save(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable Long id, @RequestBody Resource resourceDetails) {
        return resourceRepository.findById(id)
                .map(resource -> {
                    resource.setName(resourceDetails.getName());
                    resource.setDescription(resourceDetails.getDescription());
                    resource.setType(resourceDetails.getType());
                    resource.setFormat(resourceDetails.getFormat());
                    return ResponseEntity.ok(resourceRepository.save(resource));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/download")
    public ResponseEntity<Resource> incrementDownloadCount(@PathVariable Long id) {
        return resourceRepository.findById(id)
                .map(resource -> {
                    resource.setDownloadCount(resource.getDownloadCount() + 1);
                    return ResponseEntity.ok(resourceRepository.save(resource));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteResource(@PathVariable Long id) {
        return resourceRepository.findById(id)
                .map(resource -> {
                    resourceRepository.delete(resource);
                    Map<String, Boolean> response = new HashMap<>();
                    response.put("deleted", true);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
