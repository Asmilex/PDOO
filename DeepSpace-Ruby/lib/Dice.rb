#encoding: UTF-8
module Deepspace
class Dice
   def initialize
      @NHANGARSPROB  = 0.25
      @NSHIELDSPROB  = 0.25
      @NWEAPONSPROB  = 0.33
      @FIRSTSHOTPROB = 0.5
      @generator     = rand
      @EXTRAEFFICIENCYPROB = 0.8
   end

#
# ─────────────────────────────────────────────────────────────────── INITS ─────
#

   def initWithNHangars
      @generator = rand
      if (@generator < @NHANGARSPROB)
         return 0
      else
         return 1
      end
   end

   def initWithNShields
      @generator = rand
      if (@generator < @NSHIELDSPROB)
         return 0
      else
         return 1
      end
   end

   def initWithNWeapons
      @generator = rand
      if (@generator < @NWEAPONSPROB)
         return 1
      elsif (@generator < 2*@NWEAPONSPROB)
         return 2
      else
         return 3
      end
   end

#
# ──────────────────────────────────────────────────────────────────── OTROS ─────
#

   def whoStarts(nPlayers)
      return rand(nPlayers)
   end

   def firstShot
      @generator = rand
      if (@generator < @FIRSTSHOTPROB)
         return GameCharacter::SPACESTATION
      else
         return GameCharacter::ENEMYSTARSHIP
      end
   end

   def spaceStationMoves(speed)
      @generator = rand
      return (@generator < speed)
   end

   def extraEfficiency
      @generator = rand
      @generator < @EXTRAEFFICIENCYPROB
   end
end

end
