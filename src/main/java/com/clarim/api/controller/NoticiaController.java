package com.clarim.api.controller;

import com.clarim.api.dto.NoticiaRequest;
import com.clarim.api.dto.NoticiaResponse;
import com.clarim.api.dto.NoticiaResumo;
import com.clarim.api.service.NoticiaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    private final NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @GetMapping
    public List<NoticiaResumo> listar() {
        return noticiaService.listarTodas();
    }

    // GET /api/noticias/2
    @GetMapping("/{id}")
    public NoticiaResumo buscarPorId(@PathVariable Long id) {
        return noticiaService.buscarPorId(id).orElse(null);
    }

    // GET /api/noticias/buscar?termo=imprensa
    @GetMapping("/buscar")
    public List<NoticiaResumo> buscar(@RequestParam(required = false) String termo) {
        return noticiaService.listarTodas().stream()
                .filter(n -> n.titulo().toLowerCase().contains(termo.toLowerCase()))
                .toList();
    }

    @PostMapping("/criar")
    public ResponseEntity<NoticiaResponse> criar(@RequestBody @Valid NoticiaRequest noticia) {
        NoticiaResponse criada = noticiaService.criar(noticia);
        return ResponseEntity.ok(criada);
    }
}
