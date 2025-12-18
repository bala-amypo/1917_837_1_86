@RestController
@RequestMapping("/farms")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService service;

    @PostMapping
    public ResponseEntity<?> createFarm(@RequestBody FarmRequest req,
                                        Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        Farm farm = Farm.builder()
                .name(req.getName())
                .soilPH(req.getSoilPH())
                .waterLevel(req.getWaterLevel())
                .season(req.getSeason())
                .build();
        return ResponseEntity.ok(service.createFarm(farm, userId));
    }

    @GetMapping
    public ResponseEntity<?> listFarms(Authentication auth) {
        return ResponseEntity.ok(service.getFarmsByOwner((Long) auth.getPrincipal()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFarm(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFarmById(id));
    }
}
