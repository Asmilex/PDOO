#encoding: UTF-8
module DeepSpace
   class ShieldBooster
      def initialize (n, b, u)
         @name  = n
         @boost = b
         @uses  = u
      end

      def self.newCopy (s)
         t = ShieldBooster.new(s.name(), s.boost(), s.uses())
         
         return t
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
      l = ShieldBooster.new("a",1,1)
      s = ShieldBooster.newCopy(l)
   end

end
