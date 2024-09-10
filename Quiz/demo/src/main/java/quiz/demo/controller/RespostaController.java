package quiz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import quiz.demo.model.Resposta;
import quiz.demo.service.RespostaService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @GetMapping
    public List<Resposta> getAllRespostas() {
        return respostaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resposta> getRespostaById(@PathVariable Long id) {
        Optional<Resposta> resposta = respostaService.findById(id);
        return resposta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resposta> createResposta(@RequestBody Resposta resposta) {
        Resposta savedResposta = respostaService.save(resposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta> updateResposta(@PathVariable Long id, @RequestBody Resposta resposta) {
        if (!respostaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        resposta.setId(id);
        Resposta updatedResposta = respostaService.save(resposta);
        return ResponseEntity.ok(updatedResposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResposta(@PathVariable Long id) {
        if (!respostaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        respostaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
