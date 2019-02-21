#encoding: UTF-8
module DeepSpace
   class ShieldBooster
      def initialize (n, b, u)
         @name = n
         @boost = b
         @uses = u
      end

      def newCopy (s)
         @name = s.name()
         @boost = s.boost()
         @uses = s.uses()
      end

      def boost
         return @boost
      end

      def uses
         return @uses
      end

      def uselt
         if (@uses > 0)
            @uses -= 1
            return @boost
         else
            return 1.0
         end
      end

   end

end
