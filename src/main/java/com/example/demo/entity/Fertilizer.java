@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fertilizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String npkRatio;
    private String recommendedForCrops;
}
