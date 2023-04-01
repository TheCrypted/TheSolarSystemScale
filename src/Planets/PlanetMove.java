package Planets;

public class PlanetMove {
    private PlanetObj planet;
    public static double TIMESTEP = 3600*12;
    public static void updatePlanetPos(PlanetObj planet){
        planet.updateAnimation();
    }
    public static double[] ForceReturn(PlanetObj planet, PlanetObj otherPlanet){
        double relX = -planet.x + otherPlanet.x;
        double relY = -planet.y + otherPlanet.y;

        double distancePlanets = Math.sqrt(Math.pow((relX), 2) + Math.pow((relY), 2));
        double ForceG = (PlanetObj.G * otherPlanet.mass) / Math.pow(distancePlanets, 2);
        double theta = Math.atan2(relY, relX);
        double ForceY = ForceG * Math.sin(theta);
        double ForceX = ForceG * Math.cos(theta);
        double[] Forces = {ForceX, ForceY};
        return Forces;
    }
    public static void updatePlanets(PlanetObj[] Planets){
        for (PlanetObj planet : Planets) {
            if (!planet.isSun()) {
                double totalX = 0;
                double totalY = 0;
                for (PlanetObj otherPLanet : Planets) {
                    if (otherPLanet != planet) {
                        double[] Forces = ForceReturn(planet, otherPLanet);
                        totalX += Forces[0];
                        totalY += Forces[1];

                    }
                }
                planet.velY += totalY * TIMESTEP;
                planet.velX += totalX * TIMESTEP;
                planet.x += planet.velX * TIMESTEP;
                planet.y += planet.velY * TIMESTEP;

            }
        }
    }
}
