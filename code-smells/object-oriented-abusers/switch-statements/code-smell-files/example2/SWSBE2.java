import java.util.*;

class CosmeticSample {
  private final String sampleId;
  private final TestType testType;
  private final Map<String, Double> results = new LinkedHashMap<>();

  public CosmeticSample(String sampleId, TestType testType) {
    this.sampleId = sampleId;
    this.testType = testType;
  }

  public String getSampleId() {
    return sampleId;
  }

  public TestType getTestType() {
    return testType;
  }

  public void recordResult(String metric, double value) {
    results.put(metric, value);
  }

  public Map<String, Double> getResults() {
    return Collections.unmodifiableMap(results);
  }
}

class CosmeticLab {
  private final boolean isCertified;

  public CosmeticLab(boolean isCertified) {
    this.isCertified = isCertified;
  }

  public void performTest(CosmeticSample sample) {
    if (!isCertified) {
      System.out.println("Lab is not certified to run tests.");
      return;
    }

    Random rand;
    double result;

    System.out.println("Running " + sample.getTestType() + " on sample " + sample.getSampleId());
    switch (sample.getTestType()) {
      case PH_BALANCE:
        rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.PH_SEED_OFFSET);
        double reading = TestConstants.PH_MIN + rand.nextDouble() * TestConstants.PH_RANGE;
        result = Math.round(reading * TestConstants.PH_PRECISION) / TestConstants.PH_PRECISION;
        sample.recordResult("pH", result);
        break;
      case OIL_CONTENT:
        rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.OIL_SEED_OFFSET);
        double oilPerc = TestConstants.OIL_MIN + rand.nextDouble() * TestConstants.OIL_RANGE;
        result = Math.round(oilPerc * TestConstants.OIL_PRECISION) / TestConstants.OIL_PRECISION;
        sample.recordResult("Oil %", result);
        break;
      case MOISTURE_LEVEL:
        rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.MOISTURE_SEED_OFFSET);
        double moisture = TestConstants.MOISTURE_MIN + rand.nextDouble() * TestConstants.MOISTURE_RANGE;
        result = Math.round(moisture * TestConstants.MOISTURE_PRECISION) / TestConstants.MOISTURE_PRECISION;
        sample.recordResult("Moisture %", result);
        break;
      case UV_PROTECTION:
        rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.UV_SEED_OFFSET);
        result = TestConstants.SPF_MIN + rand.nextInt(TestConstants.SPF_RANGE);
        sample.recordResult("SPF Value", result);
        break;
      case STABILITY_UNDER_HEAT:
        rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.HEAT_SEED_OFFSET);
        double degradation = rand.nextDouble() * TestConstants.HEAT_MAX_DEGRADATION;
        result = Math.round(degradation * TestConstants.HEAT_PRECISION) / TestConstants.HEAT_PRECISION;
        sample.recordResult("Degradation %", result);
        break;
      case FRAGRANCE_INTENSITY:
        rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.FRAGRANCE_SEED_OFFSET);
        double score = TestConstants.FRAGRANCE_MIN + rand.nextDouble() * TestConstants.FRAGRANCE_RANGE;
        result = Math.round(score * TestConstants.FRAGRANCE_PRECISION) / TestConstants.FRAGRANCE_PRECISION;
        sample.recordResult("Scent Score", result);
        break;
      case COLOR_UNIFORMITY:
        rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.COLOR_SEED_OFFSET);
        double deltaE = TestConstants.COLOR_MIN + rand.nextDouble() * TestConstants.COLOR_RANGE;
        result = Math.round(deltaE * TestConstants.COLOR_PRECISION) / TestConstants.COLOR_PRECISION;
        sample.recordResult("ΔE", result);
        break;
      case PARTICLE_SIZE_DISTRIBUTION:
        rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.PARTICLE_SEED_OFFSET);
        double size = TestConstants.PARTICLE_MIN + rand.nextDouble() * TestConstants.PARTICLE_RANGE;
        result = Math.round(size * TestConstants.PARTICLE_PRECISION) / TestConstants.PARTICLE_PRECISION;
        sample.recordResult("Average Particle Size", result);
        break;
      default:
        System.out.println("Unknown test type: " + sample.getTestType());
    }
    System.out.println("Results: " + sample.getResults());
  }
}

enum TestType {
  PH_BALANCE,
  OIL_CONTENT,
  MOISTURE_LEVEL,
  UV_PROTECTION,
  STABILITY_UNDER_HEAT,
  FRAGRANCE_INTENSITY,
  COLOR_UNIFORMITY,
  PARTICLE_SIZE_DISTRIBUTION
}

final class TestConstants {
  private TestConstants() {}
  static final long PH_SEED_OFFSET = 0L;
  static final double PH_MIN = 4.5;
  static final double PH_RANGE = 3.5;
  static final double PH_PRECISION = 100.0;

  static final long OIL_SEED_OFFSET = 0xDEADBEEFL;
  static final double OIL_MIN = 5.0;
  static final double OIL_RANGE = 15.0;
  static final double OIL_PRECISION = 10.0;

  static final long MOISTURE_SEED_OFFSET = 0xCAFEBABEL;
  static final double MOISTURE_MIN = 3.0;
  static final double MOISTURE_RANGE = 9.0;
  static final double MOISTURE_PRECISION = 10.0;

  static final long UV_SEED_OFFSET = 0x12345678L;
  static final int SPF_MIN = 15;
  static final int SPF_RANGE = 36;

  static final long HEAT_SEED_OFFSET = 0x87654321L;
  static final double HEAT_MAX_DEGRADATION = 5.0;
  static final double HEAT_PRECISION = 100.0;

  static final long FRAGRANCE_SEED_OFFSET = 0xABCDEF01L;
  static final double FRAGRANCE_MIN = 1.0;
  static final double FRAGRANCE_RANGE = 9.0;
  static final double FRAGRANCE_PRECISION = 10.0;

  static final long COLOR_SEED_OFFSET = 0x10FEDCBAL;
  static final double COLOR_MIN = 0.5;
  static final double COLOR_RANGE = 2.5;
  static final double COLOR_PRECISION = 100.0;

  static final long PARTICLE_SEED_OFFSET = 0x0FEDCBA1L;
  static final double PARTICLE_MIN = 0.1;
  static final double PARTICLE_RANGE = 4.9;
  static final double PARTICLE_PRECISION = 100.0;
}

public class SWSBE2 {
  public static void main(String[] args) {
    CosmeticLab lab = new CosmeticLab(true);

    List<CosmeticSample> samples = Arrays.asList(
      new CosmeticSample("SMP-001", TestType.PH_BALANCE),
      new CosmeticSample("SMP-002", TestType.OIL_CONTENT),
      new CosmeticSample("SMP-003", TestType.MOISTURE_LEVEL),
      new CosmeticSample("SMP-003", TestType.UV_PROTECTION),
      new CosmeticSample("SMP-004", TestType.STABILITY_UNDER_HEAT),
      new CosmeticSample("SMP-003", TestType.FRAGRANCE_INTENSITY),
      new CosmeticSample("SMP-003", TestType.COLOR_UNIFORMITY),
      new CosmeticSample("SMP-005", TestType.PARTICLE_SIZE_DISTRIBUTION)
    );

    for (CosmeticSample sample : samples) {
      lab.performTest(sample);
      System.out.println("---");
    }
  }
}
